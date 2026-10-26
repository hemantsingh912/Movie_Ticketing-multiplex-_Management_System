package Model;

public class ScreenModel {
    private int screenId;
    private String screenName;
    private int regularSeatCount;
    private int premiumSeatCount;
    private int goldSeatCount;
    private int totalSeat;
    private String screenType;
    private String status;

    public ScreenModel() {}

    

    public ScreenModel(int screenId, String screenName, int regularSeatCount, int premiumSeatCount, int goldSeatCount,
            int totalSeat, String screenType, String status) {
        this.screenId = screenId;
        this.screenName = screenName;
        this.regularSeatCount = regularSeatCount;
        this.premiumSeatCount = premiumSeatCount;
        this.goldSeatCount = goldSeatCount;
        this.totalSeat = totalSeat;
        this.screenType = screenType;
        this.status = status;
    }

    @Override
public String toString() {
    return screenName;
}

    public int getScreenId() {
        return screenId;
    }
    public void setScreenId(int screenId) {
        this.screenId = screenId;
    }
    public String getScreenName() {
        return screenName;
    }
    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }
    public int getRegularSeatCount() {
        return regularSeatCount;
    }
    public void setRegularSeatCount(int regularSeatCount) {
        this.regularSeatCount = regularSeatCount;
    }
    public int getPremiumSeatCount() {
        return premiumSeatCount;
    }
    public void setPremiumSeatCount(int premiumSeatCount) {
        this.premiumSeatCount = premiumSeatCount;
    }
    public int getGoldSeatCount() {
        return goldSeatCount;
    }
    public void setGoldSeatCount(int goldSeatCount) {
        this.goldSeatCount = goldSeatCount;
    }
    public int getTotalSeat() {
        return totalSeat;
    }
    public void setTotalSeat(int totalSeat) {
        this.totalSeat = totalSeat;
    }
    public String getScreenType() {
        return screenType;
    }
    public void setScreenType(String screenType) {
        this.screenType = screenType;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    
}
