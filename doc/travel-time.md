---
title: travel-time v1.0.0
language_tabs:
  - shell: Shell
  - http: HTTP
  - javascript: JavaScript
  - ruby: Ruby
  - python: Python
  - php: PHP
  - java: Java
  - go: Go
toc_footers: []
includes: []
search: true
code_clipboard: true
highlight_theme: darkula
headingLevel: 2
generator: "@tarslib/widdershins v4.0.17"

---

# travel-time

> v1.0.0

Base URLs:

* <a href="http://127.0.0.1:8080">本地环境: http://127.0.0.1:8080</a>

# 用户控制层

## PATCH 修改用户

PATCH /user

> Body 请求参数

```json
{
  "id": 0,
  "homeLat": "string",
  "companyLat": "string",
  "barkId": "string"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|[UserUpdateRequest](#schemauserupdaterequest)| 否 |none|

> 返回示例

> 成功

```json
{
  "code": 0,
  "message": "",
  "data": {
    "id": 0,
    "nickname": "",
    "openId": "",
    "homeLat": "",
    "companyLat": "",
    "barkId": ""
  },
  "date": "",
  "flag": false,
  "messageId": ""
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

*priv.peixinyi.tt.pojo.Result<priv.peixinyi.tt.entity.User>*

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|

## DELETE 删除用户

DELETE /user

> Body 请求参数

```json
{
  "password": "string"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|[UserLogoffRequest](#schemauserlogoffrequest)| 否 |none|

> 返回示例

> 成功

```json
{
  "code": 0,
  "message": "",
  "data": "",
  "date": "",
  "flag": false,
  "messageId": ""
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

*priv.peixinyi.tt.pojo.Result<priv.peixinyi.tt.entity.User>*

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|

## PUT 刷新用户的OpenId

PUT /user/refreshOpenId

> 返回示例

> 成功

```json
{
  "code": 0,
  "message": "",
  "data": {
    "id": 0,
    "nickname": "",
    "openId": "",
    "homeLat": "",
    "companyLat": "",
    "barkId": ""
  },
  "date": "",
  "flag": false,
  "messageId": ""
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

*priv.peixinyi.tt.pojo.Result<priv.peixinyi.tt.entity.User>*

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|

## POST 登录

POST /user/login

> Body 请求参数

```json
{
  "name": "string",
  "password": "string"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|[UserLoginRequest](#schemauserloginrequest)| 否 |none|

> 返回示例

> 成功

```json
{
  "code": 0,
  "message": "",
  "data": {
    "id": 0,
    "nickname": "",
    "openId": "",
    "homeLat": "",
    "companyLat": "",
    "barkId": ""
  },
  "date": "",
  "flag": false,
  "messageId": ""
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

*priv.peixinyi.tt.pojo.Result<priv.peixinyi.tt.entity.User>*

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|

## POST 注册

POST /user/register

> Body 请求参数

```json
{
  "nickname": "string",
  "password": "string",
  "homeLat": "string",
  "companyLat": "string",
  "barkId": "string"
}
```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|[UserRegisterRequest](#schemauserregisterrequest)| 否 |none|

> 返回示例

> 成功

```json
{
  "code": 0,
  "message": "",
  "data": {
    "id": 0,
    "nickname": "",
    "openId": "",
    "homeLat": "",
    "companyLat": "",
    "barkId": ""
  },
  "date": "",
  "flag": false,
  "messageId": ""
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

*priv.peixinyi.tt.pojo.Result<priv.peixinyi.tt.entity.User>*

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|

# 行驶记录控制层

## POST 开始行驶

POST /travelRecord/connect

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|longitude|query|string| 是 |none|
|latitude|query|string| 是 |none|
|site|query|string| 是 |none|

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## POST 结束行驶

POST /travelRecord/disconnect

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|longitude|query|string| 是 |none|
|latitude|query|string| 是 |none|
|site|query|string| 是 |none|

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

## POST drive

POST /travelRecord/drive

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|longitude|query|string| 是 |none|
|latitude|query|string| 是 |none|
|site|query|string| 是 |none|

> 返回示例

> 200 Response

```json
{}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

# 数据模型

<h2 id="tocS_UserRegisterRequest">UserRegisterRequest</h2>

<a id="schemauserregisterrequest"></a>
<a id="schema_UserRegisterRequest"></a>
<a id="tocSuserregisterrequest"></a>
<a id="tocsuserregisterrequest"></a>

```json
{
  "nickname": "string",
  "password": "string",
  "homeLat": "string",
  "companyLat": "string",
  "barkId": "string"
}

```

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|nickname|string|false|none||用户名|
|password|string|false|none||密码|
|homeLat|string|false|none||家里的经纬度|
|companyLat|string|false|none||公司经纬度|
|barkId|string|false|none||Bark通知ID|

<h2 id="tocS_UserLogoffRequest">UserLogoffRequest</h2>

<a id="schemauserlogoffrequest"></a>
<a id="schema_UserLogoffRequest"></a>
<a id="tocSuserlogoffrequest"></a>
<a id="tocsuserlogoffrequest"></a>

```json
{
  "password": "string"
}

```

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|password|string|false|none||密码|

<h2 id="tocS_Result«String»">Result«String»</h2>

<a id="schemaresult«string»"></a>
<a id="schema_Result«String»"></a>
<a id="tocSresult«string»"></a>
<a id="tocsresult«string»"></a>

```json
{
  "code": 0,
  "message": "string",
  "data": "string",
  "date": "string",
  "flag": true,
  "messageId": "string"
}

```

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|code|integer|false|none||状态码|
|message|string|false|none||消息|
|data|string|false|none||数据|
|date|string|false|none||时间|
|flag|boolean|false|none||状态|
|messageId|string|false|none||消息ID|

<h2 id="tocS_UserUpdateRequest">UserUpdateRequest</h2>

<a id="schemauserupdaterequest"></a>
<a id="schema_UserUpdateRequest"></a>
<a id="tocSuserupdaterequest"></a>
<a id="tocsuserupdaterequest"></a>

```json
{
  "id": 0,
  "homeLat": "string",
  "companyLat": "string",
  "barkId": "string"
}

```

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|id|integer|false|none||none|
|homeLat|string|false|none||家里的经纬度|
|companyLat|string|false|none||公司经纬度|
|barkId|string|false|none||Bark通知ID|

<h2 id="tocS_UserAddRequest">UserAddRequest</h2>

<a id="schemauseraddrequest"></a>
<a id="schema_UserAddRequest"></a>
<a id="tocSuseraddrequest"></a>
<a id="tocsuseraddrequest"></a>

```json
{
  "nickname": "string",
  "password": "string",
  "homeLat": "string",
  "companyLat": "string",
  "barkId": "string"
}

```

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|nickname|string|false|none||昵称|
|password|string|false|none||密码|
|homeLat|string|false|none||家里的经纬度|
|companyLat|string|false|none||公司经纬度|
|barkId|string|false|none||Bark通知ID|

<h2 id="tocS_UserLoginRequest">UserLoginRequest</h2>

<a id="schemauserloginrequest"></a>
<a id="schema_UserLoginRequest"></a>
<a id="tocSuserloginrequest"></a>
<a id="tocsuserloginrequest"></a>

```json
{
  "name": "string",
  "password": "string"
}

```

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|name|string|false|none||用户名|
|password|string|false|none||密码|

<h2 id="tocS_Result«User»">Result«User»</h2>

<a id="schemaresult«user»"></a>
<a id="schema_Result«User»"></a>
<a id="tocSresult«user»"></a>
<a id="tocsresult«user»"></a>

```json
{
  "code": 0,
  "message": "string",
  "data": {
    "id": 0,
    "nickname": "string",
    "openId": "string",
    "homeLat": "string",
    "companyLat": "string",
    "barkId": "string"
  },
  "date": "string",
  "flag": true,
  "messageId": "string"
}

```

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|code|integer|false|none||状态码|
|message|string|false|none||消息|
|data|[User](#schemauser)|false|none||数据|
|date|string|false|none||时间|
|flag|boolean|false|none||状态|
|messageId|string|false|none||消息ID|

<h2 id="tocS_User">User</h2>

<a id="schemauser"></a>
<a id="schema_User"></a>
<a id="tocSuser"></a>
<a id="tocsuser"></a>

```json
{
  "id": 0,
  "nickname": "string",
  "openId": "string",
  "homeLat": "string",
  "companyLat": "string",
  "barkId": "string"
}

```

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|id|integer|false|none||用户ID|
|nickname|string|false|none||昵称|
|openId|string|false|none||OpenId|
|homeLat|string|false|none||家里的经纬度|
|companyLat|string|false|none||公司经纬度|
|barkId|string|false|none||Bark通知ID|

