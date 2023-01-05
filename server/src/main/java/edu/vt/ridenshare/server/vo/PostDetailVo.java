package edu.vt.ridenshare.server.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
public class PostDetailVo extends PostVo implements Serializable {

    private String phone;

    private CarVo carVo;

    /**
     * longitude and latitude
     */
    private SpotVo startSpot;

    private SpotVo endSpot;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PostDetailVo that = (PostDetailVo) o;
        return Objects.equals(startSpot, that.startSpot) && Objects.equals(endSpot, that.endSpot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), startSpot, endSpot);
    }
}
