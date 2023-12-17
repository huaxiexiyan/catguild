import json


class SignInListItemRewardItem:
    def __init__(
        self,
        **kwargs
    ):
        self.name = kwargs.get('name')
        self.nameIcon = kwargs.get('nameIcon')
        self.type = kwargs.get('type')
        self.actionText = kwargs.get('actionText')
        self.action = kwargs.get('action')
        self.status = kwargs.get('status')
        self.remind = kwargs.get('remind')
        self.remindIcon = kwargs.get('remindIcon')
        self.expire = kwargs.get('expire')
        self.position = kwargs.get('position')

    def __str__(self):
        # 将对象属性转换为JSON格式字符串
        return json.dumps(self.__dict__, ensure_ascii=False, indent=2)


class SignInListItem:
    def __init__(
        self,
        rewards: list,
        **kwargs
    ):
        self.blessing = kwargs.get('blessing')
        self.date = kwargs.get('date')
        self.day = kwargs.get('day')
        self.icon = kwargs.get('icon')
        self.status = kwargs.get('status')
        self.subtitle = kwargs.get('subtitle')
        self.theme = kwargs.get('theme')
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
        signInInfos: list,
        **kwargs
    ):
        self.isSignIn = kwargs.get('isSignIn')
        self.month = kwargs.get('month')
        self.signInCount = kwargs.get('signInCount')
        self.themeIcon = kwargs.get('themeIcon')
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
        **kwargs
    ):
        self.goodsDescription = kwargs.get('goodsDescription')
        self.goodsId = kwargs.get('goodsId')
        self.goodsName = kwargs.get('goodsName')
        self.goodsType = kwargs.get('goodsType')
        self.isReward = kwargs.get('isReward')
        self.isSignIn = kwargs.get('isSignIn')
        self.monthSubject = kwargs.get('monthSubject')
        self.saveShareBackground = kwargs.get('saveShareBackground')
        self.webAndPcSignInBackground = kwargs.get('webAndPcSignInBackground')
        self.webAndPcSignInBackgroundV2 = kwargs.get('webAndPcSignInBackgroundV2')
        self.webAndPcSignInBackgroundV2 = kwargs.get('webAndPcSignInBackgroundV2')

    def __str__(self):
        # 将对象属性转换为JSON格式字符串
        return json.dumps(self.__dict__, ensure_ascii=False, indent=2)


class CommonResponse:
    def __init__(
        self,
        **kwargs
    ):
        self.success = kwargs.get('success')
        self.code = kwargs.get('code')
        self.message = kwargs.get('message')
        self.totalCount = kwargs.get('totalCount')
        self.nextToken = kwargs.get('nextToken')
        self.maxResults = kwargs.get('maxResults')
        self.result = kwargs.get('result')
        self.arguments = kwargs.get('arguments')

    def __str__(self):
        # 将对象属性转换为JSON格式字符串
        return json.dumps(self.__dict__, ensure_ascii=False, indent=2)


class AccountTokenBO:
    def __init__(self, **kwargs):
        self.success = kwargs.get('success')
        self.name = kwargs.get('name')
        self.accessToken = kwargs.get('access_token')
        self.refreshToken = kwargs.get('refresh_token')
        self.expiresIn = kwargs.get('expires_in')
        self.message = kwargs.get('message')

    def __str__(self):
        # 将对象属性转换为JSON格式字符串
        return json.dumps(self.__dict__, ensure_ascii=False, indent=2)
