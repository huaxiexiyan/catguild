import json


class SignInListItemRewardItem:
    def __init__(
        self,
        name: str,
        nameIcon: str,
        type: str,
        actionText: str,
        action: list,
        status: str,
        remind: str,
        remindIcon: str,
        expire: str,
        position: int,
    ):
        self.name = name
        self.nameIcon = nameIcon
        self.type = type
        self.actionText = actionText
        self.action = action
        self.status = status
        self.remind = remind
        self.remindIcon = remindIcon
        self.expire = expire
        self.position = position

    def __str__(self):
        # 将对象属性转换为JSON格式字符串
        return json.dumps(self.__dict__, ensure_ascii=False, indent=2)


class SignInListItem:
    def __init__(
        self,
        blessing: str,
        date: str,
        day: str,
        icon: str,
        rewards: list,
        status,
        subtitle: str,
        theme: str,
    ):
        self.blessing = blessing
        self.date = date
        self.day = day
        self.icon = icon
        self.status = status
        self.subtitle = subtitle
        self.theme = theme
        rewardItems = []
        for item in rewards:
            rewardItems.append(SignInListItemRewardItem(**item))
        self.rewards = rewardItems

    def __str__(self):
        # 将对象属性转换为JSON格式字符串
        return json.dumps(self.__dict__, ensure_ascii=False, indent=2)


class SignInListResponse:
    def __init__(
        self,
        isSignIn: bool,
        month: bool,
        signInCount: int,
        signInInfos: list,
        themeIcon: str,
    ):
        self.isSignIn = isSignIn
        self.month = month
        self.signInCount = signInCount
        self.themeIcon = themeIcon
        signInInfoItems = []
        for item in signInInfos:
            signInInfoItems.append(SignInListItem(**item))
        self.signInInfos = signInInfoItems

    def __str__(self):
        # 将对象属性转换为JSON格式字符串
        return json.dumps(self.__dict__, ensure_ascii=False, indent=2)


class SignInGoodsResponse:
    def __init__(
        self,
        goodsDescription: str,
        goodsId: int,
        goodsName: str,
        goodsType: str,
        isReward: bool,
        isSignIn: bool,
        monthSubject: str,
        saveShareBackground: str,
        webAndPcSignInBackground: str,
        webAndPcSignInBackgroundV2: str,
    ):
        self.goodsDescription = goodsDescription
        self.goodsId = goodsId
        self.goodsName = goodsName
        self.goodsType = goodsType
        self.isReward = isReward
        self.isSignIn = isSignIn
        self.monthSubject = monthSubject
        self.saveShareBackground = saveShareBackground
        self.webAndPcSignInBackground = webAndPcSignInBackground
        self.webAndPcSignInBackgroundV2 = webAndPcSignInBackgroundV2

    def __str__(self):
        # 将对象属性转换为JSON格式字符串
        return json.dumps(self.__dict__, ensure_ascii=False, indent=2)

class CommonResponse:
    def __init__(
        self,
        success: bool,
        code: str,
        message: str,
        totalCount: str,
        nextToken: str,
        maxResults: str,
        result,
        arguments: str,
    ):
        self.success = success
        self.code = code
        self.message = message
        self.totalCount = totalCount
        self.nextToken = nextToken
        self.maxResults = maxResults
        self.result = result
        self.arguments = arguments

    def __str__(self):
        # 将对象属性转换为JSON格式字符串
        return json.dumps(self.__dict__, ensure_ascii=False, indent=2)


class AccountTokenBO:
    def __init__(self, success, name, access_token, refresh_token, expires_in, message):
        self.success = success
        self.name = name
        self.access_token = access_token
        self.refresh_token = refresh_token
        self.expires_in = expires_in
        self.message = message

    def __str__(self):
        # 将对象属性转换为JSON格式字符串
        return json.dumps(self.__dict__, ensure_ascii=False, indent=2)
