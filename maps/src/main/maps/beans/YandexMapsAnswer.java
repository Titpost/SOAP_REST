
package maps.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class YandexMapsAnswer {

    @SerializedName("code")
    @Expose
    private Integer code;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("code", code)
                .toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(code)
                .toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof YandexMapsAnswer)) {
            return false;
        }
        final YandexMapsAnswer rhs = (YandexMapsAnswer) other;
        return new EqualsBuilder()
                .append(code, rhs.code)
                .isEquals();
    }

}
