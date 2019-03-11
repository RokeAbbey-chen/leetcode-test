/**
 * 
 */
package other;

/**
 * Game log中command字段
 * 
 * @author Richard
 */
public enum ECmdType {
    UNKNOWN("Unknown"),

    // Bonus
    USER_REGISTER("用户注册"),
    FIRST_BONUS("首次登陆奖励"),
    LOGIN_BONUS("签到奖励"),
    @Deprecated
    REGRESSION_BONUS("回归奖励"),
    @Deprecated
    ROOKIE_BOX("新手宝箱"),

    // Social
    SEND_GIFT("赠送礼物"),
    SEND_GAME_GIFT("赠送游戏币礼物"),
    SEND_PK_GIFT("赠送pk礼物"),
    SEND_GIFT_LUCK("赠送礼物(幸运)"),
    SEND_GIFT_BOX("赠送礼物(礼物箱)"),
    ROBOT_SEND_GIFT("赠送礼物(机器人)"),
    RECEIVE_GIFT("接受礼物"),
    RECEIVE_GIFT_BOX("接受礼物(礼物盒)"),
    UPDATE_GIFT_BOX("修改礼物数量(礼物箱)"),
    UPDATE_GIFT_PIECE("修改礼物碎片数量"),
    COMMPOSE_GIFT("合成礼物碎片"),
    GIFT_99("礼物99次"),
    GIFT_520("礼物520次"),
    SEND_DANMU("发送弹幕"),
    LUCK_GIFT("幸运礼物"),
    BECOME_WATCHER("成为守护"),
    GET_WATCHER("被守护"),
    WATCHER_REWARD("守护奖励"),
    @Deprecated
    REGRESSION_GIFT("回归礼物"),
    @Deprecated
    REGRESSION_TASK("回归任务奖励"),
    @Deprecated
    REGRESSION_FREE("回归免费奖励"),
    ANCHOR_TASK("房间目标奖励"),

    LEVEL_UP("用户升级"),
    @Deprecated
    LEADERBOARD_BONUS("高分榜返奖"),
    @Deprecated
    LEADERBOARD_AD_BONUS("主播活动排行榜返奖"),
    @Deprecated
    KEY_EXCHANGE("钥匙转换为星星"),

    ACTIVITY_BONUS("活动排行榜奖励"), // FIXME rename
    ACTIVITYBONUS("活动奖励"), // FIXME rename
    @Deprecated
    ACTIVITY_ORDER("活动订单加金币"),
    @Deprecated
    ACTIVITY_BONUS_CHARM("活动奖励魅力"),

    SMS_CODE("发送验证码"),
    BIND_MOBILE("绑定手机"),
    UNBIND_MOBILE("解绑手机"),
    BIND_WX("绑定微信"),
    VALIDATE_SMS("验证验证码"),

    FOLLOW("关注主播"),
    FOLLOW_CANCEL("取消关注"),
    FOLLOW_ALL("一键关注"),

    // Order & Store
    GET_ORDER_ID("获取订单号"),
    GET_WX_PACKAGE("微信支付预下单"),
    SAVE_ORDER("保存预下单"),
    IAP_ORDER("苹果订单"),
    WX_ORDER("微信订单"),
    ALIPAY_ORDER("支付宝订单"),
    MIDAS_ORDER("米大师订单"),
    YEEPAY_ORDER("网银订单"),
    PAYPAL_ORDER("paypal订单"),
    WX_PUBLIC_ORDER("微信公众号订单"),
    ALIPAY_H5_ORDER("支付宝生活号订单"),
    WX_H5_ORDER("微信H5订单"),

    PAYOUT("提现申请"),
    PAYOUT_APPLY("同意提现"),
    PAYOUT_REJECT("拒绝提现"),
    PAYOUT_PAYED("提现到账"),
    PAYOUT_WX_PAYED("微信提现到账"),

    SOCIATY_PAYOUT("公会提现申请"),
    SOCIATY_PAYOUT_REJECT("公会拒绝提现"),
    SOCIATY_PAYOUT_PAYED("公会提现到账"),

    @Deprecated
    CASHOUT("兑换商品"),
    @Deprecated
    CASHOUT_APPLY("同意兑换"),
    @Deprecated
    CASHOUT_REJECT("拒绝兑换"),
    @Deprecated
    CASHOUT_PAYED("兑换到账"),
    @Deprecated
    CASHOUT_FAIL("兑换红包发生失败"),
    @Deprecated
    CASHOUT_REFUND("兑换红包超时未领取"),
    @Deprecated
    CASHOUT_GAME("抓娃娃游戏"),

    @Deprecated
    SHARE_ORDER("分享充值提成"),
    @Deprecated
    SHARE_CLICK("点击分享"),
    @Deprecated
    SHARE_REGISTER("分享注册"),
    @Deprecated
    SHARE_FIRST("微信一级分享"),
    @Deprecated
    SHARE_SECOND("微信二级分享"),
    @Deprecated
    SHARE_FIRST_BONUS("微信一级分享奖励"),
    @Deprecated
    SHARE_SECOND_BONUS("微信二级分享奖励"),

    TASK_COMPLETE("每日目标完成"),
    TASK_REWARD("每日目标领取"),
    @Deprecated
    TASK_GIFT("每日目标抽取礼物"),

    FREE_GOLD("免费金币领取"),

    // Game common
    BET_IN_GAME("游戏竞猜"),
    CLEAR_BET("取消竞猜"),
    WIN_IN_GAME("游戏奖励"),
    REWARD_IN_GAME("主播竞猜激励"),
    BALANCE_GAME_STAR("游戏结算(星星)"),
    BALANCE_GAME_GAMEGOLD("游戏结算(游戏币)"),

    @Deprecated
    START_GAME("游戏开始"),
    @Deprecated
    END_GAME("游戏结束"),

    @Deprecated
    START_LIVE("直播开始"),
    END_LIVE("直播结束"),
    LIVE_TOO_LONG("直播时间过长"),
    LIVE_GAME_TOO_LONG("直播游戏时间过长"),

    START_BET("竞猜开始"),
    END_BET("竞猜结束"),

    PLAY_GAME("进入游戏"),
    QUIT_GAME("退出游戏"),

    // PK game
    SELECT_PK_GAME("pk游戏选择"),
    CANCEL_PK_GAME("pk游戏取消"),
    START_PK_GAME("pk游戏开始"),
    END_PK_GAME("pk游戏结束"),

    EXP_PK_GAME("pk游戏经验奖励"),
    BET_IN_PK_GAME("pk游戏使用道具"),
    PRIZE_IN_PK_GAME("pk游戏抽奖"),

    // Other
    BAD_CHANGE_AMOUNT("Bad Change user item"),
    ADMIN_ADD_CHIPS("Admin Add Chips"),
    @Deprecated
    ROBOT_CHIPS_CHANGE("Robot Chips Change"),
    @Deprecated
    REDIS_INFO_CHANGE("Redis UserInfo Change"),

    LOGIN("用户登录"),
    LOGOUT("用户登出"),

    @Deprecated
    QQCARD("QQ卡卷"),

    ACHIEVEMENT("成就奖励"),
    ACHIEVEMENT_NAME("成就称号"),

    ITEM_REWARD("道具奖励"),
    ITEM_SNATCH("道具抢"),
    ITEM_USED("使用道具"),

    // 红包
    RED_ENVELOPE_OPEN("打开红包"),
    RED_ENVELOPE_SNATCH("抢红包"),
    RED_ENVELOPE_OPNE_GIFT_PIECE("开红包礼物碎片"),
    RED_ENVELOPE_SNATCH_GIFT_PIECE("抢红包礼物碎片"),
    RED_ENVELOPE_ROBOT("星球宝宝红包"),
    RED_ENVELOPE_FIRST("天降红包"),
    SHARE("分享奖励"),
    WHEEL_EXCHNAGE("转盘兑换"),
    WHEEL_BONUS("转盘奖励"),
    WHEEL_GIFT_PIECE("转盘礼物碎片"),
    LOGIN_WHEEL_BONUS("登录转盘奖励"),
    ACTIVE_WHEEL_BONUS("活动转盘奖励"),

    MATCH_LIKE("点赞"), //
    MATCH_SUCCESS("匹配成功"), //
    MATCH_START("匹配游戏开始"), //
    MATCH_MESSAGE_SUCCESS("私聊匹配成功"), //
    MATCH_FAIL("未匹配"), //
    MATCH_GAME_WIN("匹配游戏胜利"), //
    MATCH_GAME_LOSE("匹配游戏失败"), //
    MATCH_GAME_DRAW("匹配游戏平局"), //

    GOLD_CONVERT("星星兑换游戏币"),
    POINT_RACE_BOUNS("积分赛奖励"),
    INIT_WITHDRAW_LIMIT("初始额度"),
    FRIEND_TASK_REWARD("好友任务奖励"),
    MESSAGE_GIFT("消息礼物"), //

    START_PLAY("开始约玩"), //
    START_ACTIVITY_PLAY("开始活动约玩"), //
    STOP_PLAY("结束约玩"), //
    STOP_ACTIVITY_PLAY("结束活动约玩"), //
    RETURN_PLAY("陪玩返还");

    private String cmd;

    private ECmdType(String cmd) {
        this.cmd = cmd;
    }

    /**
     * @return the cmd
     */
    public String getCmd() {
        return cmd;
    }

    public static ECmdType getByCommand(String s) {
        for (ECmdType cmd : ECmdType.values()) {
            if (cmd.cmd.equals(s)) {
                return cmd;
            }
        }
        return UNKNOWN;
    }
}
