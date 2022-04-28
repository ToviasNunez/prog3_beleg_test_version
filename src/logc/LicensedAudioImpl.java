package logc;

import mediaDB.LicensedAudio;
import mediaDB.Tag;
import mediaDB.Uploader;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

/**
 * license Audio
 */
public class LicensedAudioImpl implements LicensedAudio {

    private String address;
    private Collection<Tag> tags;
    private long accessCount;
    private BigDecimal bitrate;
    private Duration duration;
    private BigDecimal size;
    private Uploader uploader;
    private Date date;
    private String holder;
    private int samplingRate;

    /**
     * @param address of the licensed audio
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @param tags
     */
    public void setTags(Collection<Tag> tags) {
        this.tags = tags;
    }

    /**
     * @param accessCount it is not allowed to negative number
     */
    public void setAccessCount(long accessCount) {
        if (accessCount < 0) {
            throw new IllegalArgumentException("AccessCount can not be negative");
        } else
            this.accessCount = accessCount;
    }


    /**
     * @param bitrate should not be smaller that xxxxx
     */
    public void setBitrate(BigDecimal bitrate) {

        if (bitrate.intValue() < 100){
            throw  new IllegalArgumentException("Bitrate should be bigger than 100");
        }else
            this.bitrate = bitrate;

    }

    /**
     * @param duration should not have a negative number and bigger than
     */
    public void setDuration(Duration duration) {
        if (duration.isNegative()){
            throw new IllegalArgumentException("Duration should not be negative");
        }else
            this.duration = duration;

    }

    /**
     * @param size should not be negative
     */
    public void setSize(BigDecimal size) {
        if (size.intValue() < 0){
            throw new IllegalArgumentException("Size should not be negative");
        }else
            this.size = size;

    }

    /**
     * @param uploader should not be null or musterman
     */
    public void setUploader(Uploader uploader) {
        if ( uploader == null || uploader.getName().equals("MUSTERMAN")){
            throw new IllegalArgumentException("Uploader should no be null or default user");
        }else
            this.uploader = uploader;

    }

    /**
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }


    /**
     * @param holder
     */
    public void setHolder(String holder) {
        this.holder = holder;
    }

    /**
     * @param samplingRate should not be negative
     */
    public void setSamplingRate(int samplingRate) {
        if (samplingRate < 0){
            throw new IllegalArgumentException("SamplingRate should not be negative");
        }else
            this.samplingRate = samplingRate;

    }

    /**
     * @return samplingRate
     */
    @Override
    public int getSamplingRate() {
        return samplingRate;
    }

    /**
     * @return address
     */
    @Override
    public String getAddress() {
        return this.address;
    }

    /**
     * @return tags
     */
    @Override
    public Collection<Tag> getTags() {
        return this.tags;
    }

    /**
     * @return accessCount
     */
    @Override
    public long getAccessCount() {
        return this.accessCount;
    }

    /**
     * @return holder
     */
    @Override
    public String getHolder() {
        return this.holder;
    }

    /**
     * @return bitrate
     */
    @Override
    public BigDecimal getBitrate() {
        return this.bitrate;
    }

    /**
     * @return duration
     */
    @Override
    public Duration getLength() {
        return this.duration;
    }

    /**
     * @return size
     */
    @Override
    public BigDecimal getSize() {
        return this.size;
    }

    /**
     * @return uploader
     */
    @Override
    public Uploader getUploader() {
        return this.uploader;
    }

    /**
     * @return date of the item was created
     */
    @Override
    public Date getUploadDate() {
        return this.date;
    }

    /**
     * @return information from the item
     */
    @Override
    public String toString() {
        return System.lineSeparator() +
                "LicensedAudioImpl{" +
                "address='" + address + '\'' +
                ", tags=" + tags +
                ", accessCount=" + accessCount +
                ", bitrate=" + bitrate +
                ", duration=" + duration +
                ", size=" + size +
                ", uploader=" + uploader +
                ", date=" + date +
                ", holder='" + holder + '\'' +
                ", samplingRate=" + samplingRate +
                '}'
                + System.lineSeparator();
    }
}
