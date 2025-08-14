- 캘린더 및 댓글 기능 구현 스프링 부트
- MySQL 사용
- 비밀번호는 사용자에게 보이지 않도록

UserEntity
#### 1-1. 유저 생성 (CREATE)
* **HTTP Method**: `POST`
* **Endpoint**: `/Users/signIn`
* **Request Body (JSON)**:
    -   `userName`: 유저 이름 (필수값)
    -   `email`: 이메일 (이메일 형식, 필수값)
    -   `password`: 비밀번호 (8자 이상, 필수값)

    ```json
    {
      "userName": "김김김",
      "email": "rlarlarla@gmail.com",
      "password": "123456789"
    }
    ```
* **Response Body (JSON)
    ```json
    {
      "id": 1,
      "userName": "김김김",
      "email": "rlarlarla@gmail.com",
      "creationTime": "2025-08-04T10:00:00.123456",
      "modificationTime": "2025-08-04T10:00:00.123456"
    }
    ```

#### 1-2. 전체 일정 조회 (READ ALL)

* **HTTP Method**: `GET`
* **Endpoint**: `/users`
* **Request**:
* **Response Body (JSON)**:
    ```json
    [
      {
      "id": 1,
      "userName": "김김김",
      "email": "rlarlarla@gmail.com",
      "creationTime": "2025-08-04T10:00:00.123456",
      "modificationTime": "2025-08-04T10:00:00.123456"
      },
      {
      "id":2 ,
      "userName": "곰곰곰",
      "email": "곰곰곰@gmail.com",
      "creationTime": "2025-08-04T10:00:00.123456",
      "modificationTime": "2025-08-04T10:00:00.123456"
      }
    ]
    ```

#### 1-3. 일정 단건 조회 (READ)
* **HTTP Method**: `GET`
* **Endpoint**: `/users/{usersId}`
* **Request**:
    -   `Path Variable`: `usersId` (조회할 유저의 ID)
* **Response Body (JSON)**:
    ```json
    {
      "id": 1,
      "userName": "김김김",
      "email": "rlarlarla@gmail.com",
      "creationTime": "2025-08-04T10:00:00.123456",
      "modificationTime": "2025-08-04T10:00:00.123456"
    }
    ```

#### 1-4. 일정 수정 (UPDATE)

* **API명**: 일정 수정
* **HTTP Method**: `PUT`
* **Endpoint**: `/users/{userId}`
* **Request Body (JSON)**:
    -   `userName`: 유저 이름 (필수값)
    -   `email`: 이메일 (이메일 형식, 필수값)
    -   `password`: 비밀번호 (8자 이상, 필수값)
* **Response Body (JSON)**:
    -   **성공 (HTTP 200 OK)**
    ```json
    {
      "id": 1,
      "userName": "김김김",
      "email": "rlarlarla@gmail.com",
      "creationTime": "2025-08-04T10:00:00.123456",
      "modificationTime": "2025-08-04T10:00:00.123456"
    }

#### 1-5. 일정 삭제 (DELETE)
* **HTTP Method**: `DELETE`
* **Endpoint**: `/users/{userId}`
* **Request Body (JSON)**:
    -   `password`: 일정 삭제를 위한 비밀번호 (필수값)
    ```json
    {
      "password": "1234"
    }
    ```

### 2. 일정 기능
#### 2-1. 일정 생성 (CREATE)
* **HTTP Method**: `POST`
* **Endpoint**: `/users/{usersId}/calendars`
* **Request Body (JSON)**:
    -   `title`: 댓글 내용 (최대 30자 이내)
    -   `content`: 작성자명 (최대 200자 이내)
    ```json
    {
      "title": "좋은 내용 감사합니다!",
      "content": "내용이다."
    }
    ```
* **Response Body (JSON)**:
    -   **성공 (HTTP 200 OK)**
    ```json
    {
      "id": 1,
      "content": "좋은 내용 감사합니다!",
      "name": "박영희",
      "creationTime": "...",
      "modificationTime": "..."
      "comments" : []
    }
#### 2-2. 일정 읽기 (GetAllCalendarsByUserId)
* **HTTP Method**: `GET`
* **Endpoint**: `/users/{userId}/calendars`
* **Response Body (JSON)**:
    -   **성공 (HTTP 200 OK)**
    ```json
    {
      "id": 1,
      "content": "좋은 내용 감사합니다!",
      "name": "박영희",
      "creationTime": "...",
      "modificationTime": "..."
      "comments" : []
    }
#### 2-3. 일정 읽기 (getCalendarById)
* **HTTP Method**: `GET`
* **Endpoint**: `/calendars/{calendarsId}`
* **Response Body (JSON)**:
    -   **성공 (HTTP 200 OK)**
    ```json
    {
      "id": 1,
      "content": "좋은 내용 감사합니다!",
      "name": "박영희",
      "creationTime": "...",
      "modificationTime": "...",
      "comments" : []
    }
#### 2-3. 일정 삭제 (DELETE)
* **HTTP Method**: `DELETE`
* **Endpoint**: `/comments/{commentId}`

* **Response Body**:
    -   **성공 (HTTP 204 No Content)**
        -   성공적으로 삭제되면 본문 없이 응답합니다.

#### 3-1. 댓글 생성 (CREATE)
* **HTTP Method**: `POST`
* **Endpoint**: `/users/user{userId}/calendars/{calendarsId}/comments`
* **Request Body (JSON)**:
    -   `content`: 작성자명 (최대 200자 이내)
    ```json
    {
      "id" : 1,
      "content": "내용이다.",
      "creationTime": "...",
      "modificationTime": "..."
    }
    ```
#### 3-1. 댓글 읽기 (READ)
* **HTTP Method**: `GET`
* **Endpoint**: `/users/calendars/{calendarsId}/comments`
* **Response Body (JSON)**:
    -   **성공 (HTTP 200 OK)**
    ```json
    {
      "id": 1,
      "content": "좋은 내용 감사합니다!",
      "creationTime": "...",
      "modificationTime": "...",
    }


### 4. ERD (Entity-Relationship Diagram)

## ERD 다이어그램 (PlantUML)
```plantuml

entity "UserEntity" as user {
  * id : Long <<PK>>
  --
  userName : String
  email : String (unique, not null)
  password : String
  createdDate : LocalDateTime
  modifiedDate : LocalDateTime
}

entity "CalendarEntity" as calendar {
  * id : Long <<PK>>
  --
  title : String
  content : String
  createdDate : LocalDateTime
  modifiedDate : LocalDateTime
}

entity "CommentEntity" as comment {
  * id : Long <<PK>>
  --
  content : String
  createdDate : LocalDateTime
  modifiedDate : LocalDateTime
}

user ||--o{ calendar : "1:N"
calendar ||--o{ comment : "1:N"
user ||--o{ comment : "1:N"