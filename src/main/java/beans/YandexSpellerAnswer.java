
package beans;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class YandexSpellerAnswer {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("pos")
    @Expose
    private Integer pos;
    @SerializedName("row")
    @Expose
    private Integer row;
    @SerializedName("col")
    @Expose
    private Integer col;
    @SerializedName("len")
    @Expose
    private Integer len;
    @SerializedName("word")
    @Expose
    public String word;
    @SerializedName("s")
    @Expose
    public final List<String> s = new ArrayList<>();

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("code", code)
                .append("pos",  pos)
                .append("row",  row)
                .append("col",  col)
                .append("len",  len)
                .append("word", word)
                .append("s",    s)
                .toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(col)
                .append(code)
                .append(s)
                .append(len)
                .append(pos)
                .append(row)
                .append(word)
                .toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof YandexSpellerAnswer)) {
            return false;
        }
        final YandexSpellerAnswer rhs = (YandexSpellerAnswer) other;
        return new EqualsBuilder()
                .append(col,  rhs.col)
                .append(code, rhs.code)
                .append(s,    rhs.s)
                .append(len,  rhs.len)
                .append(pos,  rhs.pos)
                .append(row,  rhs.row)
                .append(word, rhs.word)
                .isEquals();
    }

}
