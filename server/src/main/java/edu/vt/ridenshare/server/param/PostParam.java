package edu.vt.ridenshare.server.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PostParam {

    /**
     * preferred start time
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
     * preferred end time
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * start spot id
     */
    private Integer spotIdStart;
    /**
     * end spot id
     */
    private Integer spotIdEnd;

    /**
     * 0: driver, 1: passenger
     */
    private Integer type;

    private Integer page;

    private Integer size;
}
