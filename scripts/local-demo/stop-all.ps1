# 停止本地演示启动的全部服务

Get-CimInstance Win32_Process -Filter "Name='java.exe'" |
  Where-Object { $_.CommandLine -match 'com\.wust30405\.ricp\.' } |
  ForEach-Object {
    Write-Host ("stop PID {0}" -f $_.ProcessId)
    Stop-Process -Id $_.ProcessId -Force
  }

Write-Host "done"
