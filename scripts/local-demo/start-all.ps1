# 本地演示：启动全部服务（H2 内存库，无需 MySQL / Nacos）
#
# 用法（在仓库根目录执行）：
#   powershell -ExecutionPolicy Bypass -File scripts\local-demo\start-all.ps1
#
# 前置：
#   - JDK 8（Spring Boot 2.3 不支持 JDK 17+），已设 JAVA_HOME 或 java 在 PATH
#   - Maven 3.6+，mvn 在 PATH
#   - 已构建过一次：mvn -DskipTests install
#
# 停止：scripts\local-demo\stop-all.ps1

$ErrorActionPreference = "Stop"

$repoRoot = (Resolve-Path "$PSScriptRoot\..\..").Path
$java = if ($env:JAVA_HOME) { Join-Path $env:JAVA_HOME "bin\java.exe" } else { "java" }

if (-not (Test-Path "$repoRoot\ricp-user\target\classes")) {
  Write-Host "未找到编译产物，请先在仓库根目录执行：mvn -DskipTests install" -ForegroundColor Yellow
  exit 1
}

# 依赖 jar 列表：首次运行时自动生成（输出路径用绝对路径，避免 Maven 按模块目录解析）
$depsFile = Join-Path $PSScriptRoot "classpath.txt"
if (-not (Test-Path $depsFile)) {
  $mvn = Get-Command mvn -ErrorAction SilentlyContinue
  if (-not $mvn) {
    Write-Host "缺少 classpath.txt 且找不到 mvn，请手动执行：" -ForegroundColor Yellow
    Write-Host "  mvn -pl ricp-user dependency:build-classpath -Dmdep.outputFile=$depsFile"
    exit 1
  }
  Write-Host "生成依赖清单（首次运行，约 10 秒）..."
  Push-Location $repoRoot
  & mvn -B -q -pl ricp-user dependency:build-classpath "-Dmdep.outputFile=$depsFile"
  Pop-Location
  if (-not (Test-Path $depsFile)) { Write-Host "生成 classpath.txt 失败" -ForegroundColor Red; exit 1 }
}

$deps = (Get-Content $depsFile -Raw).Trim()

# 四个服务共用的演示参数：H2 内存库 + 关闭 Nacos
$commonArgs = @(
  "-Dspring.datasource.url=jdbc:h2:mem:ricp;MODE=MySQL;DB_CLOSE_DELAY=-1;DATABASE_TO_LOWER=TRUE",
  "-Dspring.datasource.driver-class-name=org.h2.Driver",
  "-Dspring.datasource.username=sa",
  "-Dspring.datasource.password=",
  "-Dspring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
  "-Dspring.jpa.hibernate.ddl-auto=update",
  "-Dspring.jpa.show-sql=false",
  "-Dspring.cloud.nacos.discovery.enabled=false",
  "-Dspring.cloud.nacos.config.enabled=false",
  "-Dpagehelper.helperDialect=h2"
)

$services = @(
  @{ name = "user";   port = 8081; main = "com.wust30405.ricp.user.UserApplication";     extra = @() },
  @{ name = "task";   port = 8082; main = "com.wust30405.ricp.task.TaskApplication";     extra = @() },
  @{ name = "plan";   port = 8083; main = "com.wust30405.ricp.plan.PlanApplication";     extra = @("-Dricp.quartz.config=/quartz-demo.properties") },
  @{ name = "record"; port = 8086; main = "com.wust30405.ricp.record.RecordApplication"; extra = @() }
)

New-Item -ItemType Directory -Force -Path "$repoRoot\logs" | Out-Null

foreach ($s in $services) {
  # 该模块自己的 classes 必须排在最前，否则会读到别的模块的 application.yml
  $cp = "$repoRoot\ricp-$($s.name)\target\classes;" +
        "$repoRoot\ricp-common\target\classes;" +
        "$repoRoot\ricp-task\target\classes;" +
        "$repoRoot\ricp-plan\target\classes;" +
        "$repoRoot\ricp-record\target\classes;" + $deps

  $args = $commonArgs + $s.extra + @("-Dserver.port=$($s.port)", "-cp", $cp, $s.main)
  $log = "$repoRoot\logs\local-demo-$($s.name).log"

  Start-Process -FilePath $java -ArgumentList $args `
    -RedirectStandardOutput $log -RedirectStandardError "$log.err" -WindowStyle Hidden

  Write-Host ("started {0,-7} :{1}" -f $s.name, $s.port)
}

Write-Host ""
Write-Host "等待 20-30 秒后打开 http://localhost:8081" -ForegroundColor Green
Write-Host "日志：$repoRoot\logs\local-demo-*.log"
