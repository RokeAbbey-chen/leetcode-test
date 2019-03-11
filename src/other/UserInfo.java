package other;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UserInfo implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    /* 基本信息 */
    private Long userId;
    private String name;
    private String iconBig;
    private String gender;
    private String birth;
    private String country;
    private String province;
    private String city;
    private Integer type;
    private Integer role;
    private Integer accountType;
    private String message;
    private Integer level = 0;
    private Integer status;
    private String token;
    private Integer platform;
    private String source;
    private Integer sourceType;
    private String lastSource;

    private Integer gameAppId;
    private String deviceId;

    /* 账户信息 */
    private Long paoGold = 0L;
    private Double rmb = 0D;

    private int loginBonus; // 是否可以签到
    private Long bonusDate; // 上次签到时间
    private int rewardDay;// 领取 天数

    private boolean online;

    private Double orderTotal; // 总充值金额
    private Integer orderTimes; // 充值次数

    private String wxUnionId;

    private Long loginDate;
    private Long createDate;
    private Long updateDate;
    private Long gameLoginTime = 0L;
    private int gameTime;
    private String wxOpenId;
    private String wxSessionKey;
    private String wxJSCode;

    // use for game log
    private String ip;
    private String ua;
    private String location;
    private String locationCity;

    // Reids hash 同步
    private Map<String, String> cacheMap;
    private Set<String> removeSet;

    // 邀请人id
    private long shareUserId;

    // 累计登陆天数
    private int totalLoginDays;

    // 匹配
    private int matchTime;
    private int matchWinTime;

    private long range;

    // 1 不在游戏 2 在游戏
    private int gameStatus = 1;

    private String hashKey;

    private int freeTime;

    private int current;// 当前关数

    private int reviveTimes;// 复活卡数

    private String previousMsg;// 上一关信息

    private int isAlive;// 答题状态：1-正常，0-失败

    private boolean isAnswer;

    private long questionTime;// 给提时间

    private int sendStatus;// 模板发送状态

    private int isReceiveBoy = 0; // 是否领取过宝箱

    private String gameUrl;// 游戏地址

    private int petRevives;// 萌宠单人复活次数

    private Integer ladderId;// 天梯赛等级

    private Integer star; // 星星数

    private Long ladderDate; // 天梯赛获取时间

    private Long dressUpId;// 使用装扮id

    private String winRate;// 胜率

    private long gameTotal;// 总场数

    private long gameWin;// 胜场

    private long continuousWin;// 连胜

    private int age;// 年龄

    private String wxNum;// 微信号

    private String lobbyOpenId;// 大厅openid

    private String encryptedStr;//

    private String iv;

    private String lng;// 经度

    private String lat;// 纬度

    public void initMap() {

        this.cacheMap = new HashMap<>();
        this.removeSet = new HashSet<>();
    }

    public void putMap(String key, Object value) {

        if (cacheMap != null) {
            if (value == null) {
                removeSet.add(key);
                cacheMap.remove(key);
            } else {
                cacheMap.put(key, String.valueOf(value));
                removeSet.remove(key);
            }
        }
    }

    public UserInfo() {
        super();
    }

    public UserInfo(long userId) {
        super();
        this.userId = userId;
    }

    public UserInfo(Map<String, String> map) {
        super();
//        MergeUtil.mapToUserInfo(map, this);
        this.initMap();
    }

    public void setUserId(Long userId) {
        this.userId = userId;
        putMap("userId", userId);
    }

    public void setName(String name) {
        this.name = name;
        putMap("name", name);
    }

    public void setGameAppId(Integer gameAppId) {
        this.gameAppId = gameAppId;
        putMap("gameAppId", gameAppId);
    }

    public void setRewardDay(int rewardDay) {
        this.rewardDay = rewardDay;
        putMap("rewarDay", rewardDay);
    }

    public void setIconBig(String iconBig) {
        this.iconBig = iconBig;
        putMap("iconBig", iconBig);
    }

    public void setGender(String gender) {
        this.gender = gender;
        putMap("gender", gender);
    }

    public void setBirth(String birth) {
        this.birth = birth;
        putMap("birth", birth);
    }

    public void setCountry(String country) {
        this.country = country;
        putMap("country", country);
    }

    public void setCity(String city) {
        this.city = city;
        putMap("city", city);
    }

    public void setType(Integer type) {
        this.type = type;
        putMap("type", type);
    }

    public void setRole(Integer role) {
        this.role = role;
        putMap("role", role);
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
        putMap("accountType", accountType);
    }

    public void setMessage(String message) {
        this.message = message;
        putMap("message", message);
    }

    public void setToken(String token) {
        this.token = token;
        putMap("token", token);
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
        putMap("platform", platform);
    }

    public void setSource(String source) {
        this.source = source;
        putMap("source", source);
    }

    public void setLoginDate(Long loginDate) {
        this.loginDate = loginDate;
        putMap("loginDate", loginDate);
    }

    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
        putMap("updateDate", updateDate);
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
        putMap("createDate", createDate);
    }

    public void setGameLoginTime(Long gameLoginTime) {
        this.gameLoginTime = gameLoginTime;
        putMap("gameLoginTime", gameLoginTime);
    }

    public void setIp(String ip) {
        this.ip = ip;
        putMap("ip", ip);
    }

    public void setUa(String ua) {
        this.ua = ua;
        putMap("ua", ua);
    }

    public void setOnline(boolean online) {
        this.online = online;
        putMap("online", online);
    }

    public void setHashKey(String hashKey) {
        this.hashKey = hashKey;
        putMap("hashKey", hashKey);
    }

    public void setWxUnionId(String wxUnionId) {
        this.wxUnionId = wxUnionId;
        putMap("wxUnionId", wxUnionId);
    }

    public void setStatus(Integer status) {
        this.status = status;
        putMap("status", status);
    }

    public void setAnswer(boolean isAnswer) {
        this.isAnswer = isAnswer;
        putMap("answer", isAnswer);
    }

    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
        putMap("wxOpenId", wxOpenId);
    }

    public void setLastSource(String lastSource) {
        this.lastSource = lastSource;
        putMap("lastSource", lastSource);
    }

    public void setGameTime(int gameTime) {
        this.gameTime = gameTime;
        putMap("gameTime", gameTime);
    }

    public void setLocation(String location) {
        this.location = location;
        putMap("location", location);
    }

    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
        putMap("locationCity", locationCity);
    }

    public void setShareUserId(long shareUserId) {
        this.shareUserId = shareUserId;
        putMap("shareUserId", shareUserId);
    }

    public void setTotalLoginDays(int totalLoginDays) {
        this.totalLoginDays = totalLoginDays;
        putMap("totalLoginDays", totalLoginDays);
    }

    public void setMatchTime(int matchTime) {
        this.matchTime = matchTime;
        putMap("matchTime", matchTime);
    }

    public void setMatchWinTime(int matchWinTime) {
        this.matchWinTime = matchWinTime;
        putMap("matchWinTime", matchWinTime);
    }

    public void setFreeTime(int freeTime) {
        this.freeTime = freeTime;
        putMap("freeTime", freeTime);
    }

    public void setCurrent(int current) {
        this.current = current;
        putMap("current", current);
    }

    public void setReviveTimes(int reviveTimes) {
        this.reviveTimes = reviveTimes;
        putMap("reviveTimes", reviveTimes);
    }

    public void setPreviousMsg(String previousMsg) {
        this.previousMsg = previousMsg;
        putMap("previousMsg", previousMsg);
    }

    public void setIsAlive(int isAlive) {
        this.isAlive = isAlive;
        putMap("isAlive", isAlive);
    }

    public void setQuestionTime(long questionTime) {
        this.questionTime = questionTime;
        putMap("questionTime", questionTime);
    }

    public void setWxSessionKey(String wxSessionKey) {
        this.wxSessionKey = wxSessionKey;
        putMap("wxSessionKey", wxSessionKey);
    }

    public void setH5Openid(String h5Openid) {
        putMap("h5Openid", h5Openid);
    }

    public void setSendStatus(int sendStatus) {
        this.sendStatus = sendStatus;
        putMap("sendStatus", sendStatus);
    }

    public void setIsReceiveBoy(int isReceiveBoy) {
        this.isReceiveBoy = isReceiveBoy;
        putMap("isReceiveBoy", isReceiveBoy);
    }

    public void setGameUrl(String gameUrl) {
        this.gameUrl = gameUrl;
        putMap("gameUrl", gameUrl);
    }

    public void setProvince(String province) {
        this.province = province;
        putMap("province", province);
    }

    public void setPetRevives(int petRevives) {
        this.petRevives = petRevives;
        putMap("petRevives", petRevives);
    }

    public void setAge(int age) {
        this.age = age;
        putMap("age", age);
    }

    public void setWxNum(String wxNum) {
        this.wxNum = wxNum;
        putMap("wxNum", wxNum);
    }

    public void setWxJSCode(String wxJSCode) {
        this.wxJSCode = wxJSCode;
        putMap("wxJSCode", wxJSCode);
    }

    public void setLobbyOpenId(String lobbyOpenId) {
        this.lobbyOpenId = lobbyOpenId;
        putMap("lobbyOpenId", lobbyOpenId);
    }

    public void setEncryptedStr(String encryptedStr) {
        this.encryptedStr = encryptedStr;
        putMap("encryptedStr", encryptedStr);
    }

    public void setLadderDate(Long ladderDate) {
        this.ladderDate = ladderDate;
        putMap("ladderDate", ladderDate);
    }

    /* AUTMIC FIELDS SET */

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
        putMap("deviceId", deviceId);
    }

    public void setDressUpId(Long dressUpId) {
        this.dressUpId = dressUpId;
        putMap("dressUpId", dressUpId);
    }

    public void setOrderTimes(Integer orderTimes) {
        this.orderTimes = orderTimes;
        putMap("orderTimes", orderTimes);
    }

    public void setLoginBonus(int loginBonus) {
        this.loginBonus = loginBonus;
        putMap("loginBonus", loginBonus);
    }

    public void setBonusDate(Long bonusDate) {
        this.bonusDate = bonusDate;
        putMap("bonusDate", bonusDate);
    }

    public void setOrderTotal(Double orderTotal) {
        this.orderTotal = orderTotal;
        putMap("orderTotal", orderTotal);
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
        putMap("sourceType", sourceType);
    }

    public void setIv(String iv) {
        this.iv = iv;
        putMap("iv", iv);
    }

    public void setLng(String lng) {
        this.lng = lng;
        putMap("lng", lng);
    }

    public void setLat(String lat) {
        this.lat = lat;
        putMap("lat", lat);
    }

    public void setPaoGold(Long paoGold) {
        this.paoGold = paoGold;
    }

    public void addPaoGold(Long paoGold) {
        this.paoGold = this.paoGold + paoGold;
    }

    public void setRmb(Double rmb) {
        this.rmb = rmb;
    }

    public void addRmb(Double rmb) {
        this.rmb = this.rmb + rmb;
    }
    /* AUTMIC FIELDS SET */

    /* GET METHODS */
    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name != null ? name : "";
    }

    public String getIconBig() {
        return iconBig != null ? iconBig : "";
    }

    public String getCover() {
        return getIconBig();
    }

    public String getGender() {
        return gender;
    }

    public String getBirth() {
        return birth;
    }

    public String getCountry() {
        return country;
    }

    public String getWxUnionId() {
//        return StringUtils.isNotBlank(wxUnionId) ? wxUnionId : null;
        return wxUnionId;
    }

    public String getCity() {
        return city;
    }

    public Integer getType() {
        return type ;
    }

    public Integer getRole() {
        return role != null ? role : 0;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }

    public Integer getPlatform() {
        return platform != null ? platform : 0;
    }

    public String getSource() {
        return source;
    }

    public Long getLoginDate() {
        return loginDate == null ? 0 : loginDate;
    }

    public Long getUpdateDate() {
        return updateDate != null ? updateDate : 0;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public Long getGameLoginTime() {
        return gameLoginTime;
    }

    public String getIp() {
        return ip != null && ip.length() < 20 ? ip : null;
    }

    public String getUa() {
        return ua;
    }

    public boolean isOnline() {
        return online;
    }

    public Integer getStatus() {
        // 默认启用
        return status != null ? status : 1;
    }

    public String getWxOpenId() {
        return wxOpenId;
    }

    public String getLastSource() {
        return lastSource == null ? source : lastSource;
    }

    public int getGameTime() {
        return gameTime;
    }

    public String getLocation() {
        return location;
    }

    public String getLocationCity() {
        return locationCity;
    }

    public Map<String, String> getCacheMap() {
        return cacheMap;
    }

    public void setCacheMap(Map<String, String> cacheMap) {
        this.cacheMap = cacheMap;
    }

    public Set<String> getRemoveSet() {
        return removeSet;
    }

    public void setRemoveSet(Set<String> removeSet) {
        this.removeSet = removeSet;
    }

    public long getShareUserId() {
        return shareUserId;
    }

    public int getTotalLoginDays() {
        return totalLoginDays;
    }

    public int getMatchTime() {
        return matchTime;
    }

    public int getMatchWinTime() {
        return matchWinTime;
    }

    public Double getRmb() {
        if (rmb == null) {
            return rmb = 0D;
        } else {
            BigDecimal decimal = new BigDecimal(rmb);
            return decimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        }
    }

    public long getRange() {
        return range;
    }

    public void setRange(long range) {
        this.range = range;
    }

    public int getRewardDay() {
        return rewardDay;
    }

    public int getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(int gameStatus) {
        this.gameStatus = gameStatus;
    }

    public String getHashKey() {
        return hashKey;
    }

    public int getFreeTime() {
        return freeTime;
    }

    public Integer getGameAppId() {
        return gameAppId == null ? 1 : gameAppId;
    }

    public int[] getGameFreeTime() {
        return new int[] { 0, 0, 0 };
    }

    public int getCurrent() {
        return current > 700 ? 701 : current;
    }

    public int getReviveTimes() {
        return reviveTimes;
    }

    public String getPreviousMsg() {
        return previousMsg;
    }

    public int getIsAlive() {
        return isAlive;
    }

    public boolean isAnswer() {
        return isAnswer;
    }

    public long getQuestionTime() {
        return questionTime;
    }

    public String getWxSessionKey() {
        return wxSessionKey;
    }

    public int getSendStatus() {
        return sendStatus;
    }

    public int getIsReceiveBoy() {
        return isReceiveBoy;
    }

    public String getGameUrl() {
        return gameUrl;
    }

    public String getProvince() {
        return province;
    }

    public int getPetRevives() {
        return petRevives;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public Long getDressUpId() {
        return dressUpId;
    }

    public Long getPaoGold() {
        return paoGold == null ? 0 : paoGold;
    }

    public Long getBonusDate() {
        return bonusDate == null ? 0 : bonusDate;
    }

    public int getLoginBonus() {
        return loginBonus;
    }

    public Double getOrderTotal() {
        return orderTotal;
    }

    public int getSourceType() {
        return sourceType == null ? 0 : sourceType;
    }

    public Integer getOrderTimes() {
        return orderTimes;
    }

    public String getWinRate() {
        return winRate;
    }

    public void setWinRate(String winRate) {
        this.winRate = winRate;
    }

    public long getGameTotal() {
        return gameTotal;
    }

    public void setGameTotal(long gameTotal) {
        this.gameTotal = gameTotal;
    }

    public long getGameWin() {
        return gameWin;
    }

    public void setGameWin(long gameWin) {
        this.gameWin = gameWin;
    }

    public long getContinuousWin() {
        return continuousWin;
    }

    public void setContinuousWin(long continuousWin) {
        this.continuousWin = continuousWin;
    }

    public int getAge() {
        return age;
    }

    public String getWxNum() {
        return wxNum;
    }

    public String getLobbyOpenId() {
        return lobbyOpenId;
    }

    public Integer getLadderId() {
        return ladderId;
    }

    public void setLadderId(Integer ladderId) {
        this.ladderId = ladderId;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public String getWxJSCode() {
        return wxJSCode;
    }

    public String getEncryptedStr() {
        return encryptedStr;
    }

    public String getIv() {
        return iv;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Long getLadderDate() {
        return ladderDate;
    }

    public String getLng() {
        return lng;
    }

    public String getLat() {
        return lat;
    }

    @Override
    public UserInfo clone() {
        try {
            return (UserInfo) super.clone();
        } catch (CloneNotSupportedException e) {
//            XqtvLogger.error(getClass(), "Clone failed!");
            return new UserInfo();
        }
    }
}
