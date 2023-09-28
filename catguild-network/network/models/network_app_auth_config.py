import json


class NetworkAppAuthConfig:
    def __init__(self, id: None, cTime: None, tenantId: None, userId: None, networkAppType: None, accessToken: None,
                 refreshToken: None, ctime: None, expiresIn: None):
        self.id = id
        self.tenantId = tenantId
        self.userId = userId
        self.networkAppType = networkAppType
        self.accessToken = accessToken
        self.refreshToken = refreshToken
        self.expiresIn = expiresIn
        self.cTime = cTime
        self.ctime = ctime

    def __str__(self):
        # 将对象属性转换为JSON格式字符串
        return json.dumps(self.__dict__, ensure_ascii=False, indent=2)

    @classmethod
    def from_json_str(cls, json_str):
        # 从JSON字符串创建NetworkAppAuthConfig对象
        json_data = json.loads(json_str)
        return cls(**json_data)
