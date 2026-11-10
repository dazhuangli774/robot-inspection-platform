package com.wust30405.ricp.task.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "inspection_map")
@Data
public class Map {
    public void Map(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "map_id")
    private Integer mapId;

    @Column(name = "map_name")
    private String mapName;

    @Column(name = "map_coordinate")
    private String mapCoordinate;

    @Column(name = "map_saved_id")
    private String mapSavedId;

    @CreatedBy
    @Column(name = "create_by")
    private String createBy;

    @Column(name = "create_time")
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;
}
