# 일정 관리 앱 API 명세서

## 1. 공통 정보

- **Base URL:** `/api/schedules`
- **Content-Type:** `application/json`
- **인증/인가:** 별도 로그인 인증 없음
- **비밀번호:** 일정 수정·삭제 시 저장된 비밀번호와 비교
- **응답 데이터:** 비밀번호(`password`) 제외
- **입력값 검증:** `@Valid`를 통한 필수값 및 글자 수 검증

### 입력값 검증 규칙

| 대상 | 필수 조건 | 길이 제한 |
|---|---|---|
| 일정 제목 (`title`) | 필수 | 최대 30자 |
| 일정 내용 (`contents`) | 필수 | 최대 200자 |
| 댓글 내용 (`contents`) | 필수 | 최대 100자 |
| 작성자명 (`writer`) | 필수 | 제한 없음 |
| 비밀번호 (`password`) | 필수 | 제한 없음 |

필수 문자열은 `null`, 빈 문자열, 공백만 입력한 문자열을 허용하지 않는다. 검증에 실패하면 `400 Bad Request`가 반환된다.

## 2. 일정 API

| 기능 | Method | URL | 성공 응답 |
|---|---|---|---|
| 일정 생성 | POST | `/api/schedules` | 200 OK 또는 실제 응답 상태 |
| 전체 일정 조회 | GET | `/api/schedules` | 200 OK |
| 일정 단건 조회 | GET | `/api/schedules/{id}` | 200 OK |
| 일정 수정 | PATCH | `/api/schedules/{id}` | 200 OK |
| 일정 삭제 | DELETE | `/api/schedules/{id}` | 204 No Content |

### 2-1. 일정 생성

**Request**

`POST /api/schedules`

```json
{
  "title": "Spring 공부",
  "contents": "JPA와 Spring MVC 학습",
  "writer": "김개발",
  "password": "1234"
}
```

**동작**
- 제목·내용·작성자명·비밀번호를 검증한다.
- ID를 자동 생성한다.
- 생성 시 작성일과 수정일을 설정한다.
- 응답에는 비밀번호를 포함하지 않는다.

### 2-2. 전체 일정 조회

**Request**

`GET /api/schedules`

작성자명 필터링:

`GET /api/schedules?writer=김개발`

**동작**
- 작성자명 조건이 없으면 전체 일정을 조회한다.
- `writer`가 제공되면 작성자명으로 필터링한다.
- 수정일(`modifiedAt`) 기준 내림차순으로 정렬한다.
- 응답에는 비밀번호를 포함하지 않는다.

### 2-3. 일정 단건 조회

**Request**

`GET /api/schedules/{id}`

예시: `GET /api/schedules/1`

**동작**
- URL의 ID에 해당하는 일정 정보를 반환한다.
- 응답에는 비밀번호를 포함하지 않는다.

### 2-4. 일정 수정

**Request**

`PATCH /api/schedules/{id}`

현재 DTO 검증 조건을 충족하는 요청 예시:

```json
{
  "title": "Spring MVC 복습",
  "contents": "JPA와 Spring MVC 학습",
  "writer": "김개발",
  "password": "1234"
}
```

**동작**
- 요청 DTO의 필수값과 길이를 검증한다.
- 서비스에서 비밀번호를 확인한다.
- 수정일(`modifiedAt`)을 갱신한다.
- 응답에는 비밀번호를 포함하지 않는다.

**주의:** 현재 `CalendarRequestDto` 전체에 `@Valid`가 적용되어 있어 제목·내용·작성자명·비밀번호가 모두 필요하다. 실제 수정 가능 필드는 `CalendarService` 구현에 따라 확인한다.

### 2-5. 일정 삭제

**Request**

`DELETE /api/schedules/{id}`

현재 DTO 검증 조건을 충족하는 요청 예시:

```json
{
  "title": "Spring 공부",
  "contents": "JPA와 Spring MVC 학습",
  "writer": "김개발",
  "password": "1234"
}
```

**동작**
- 요청 DTO의 필수값과 길이를 검증한다.
- 서비스에서 비밀번호를 확인한다.
- 삭제 성공 시 응답 본문 없이 `204 No Content`를 반환한다.

**주의:** 현재 삭제 메서드도 `CalendarRequestDto` 전체를 검증하므로 모든 필드를 보내야 한다. 비밀번호만 보내는 형태로 변경하려면 별도 DTO를 사용하는 방식 등을 고려할 수 있다.

## 3. 댓글 API

### 3-1. 댓글 생성

| 항목 | 내용 |
|---|---|
| Method | POST |
| URL | `/api/schedules/{scheduleId}/comments` |
| 성공 응답 | 201 Created |

**Request**

```json
{
  "contents": "좋은 일정이네요.",
  "writer": "김개발",
  "password": "1234"
}
```

**검증 조건**
- 댓글 내용 필수, 최대 100자
- 작성자명 필수
- 비밀번호 필수

요청 검증에 실패하면 `400 Bad Request`가 반환된다.

## 4. 공통 오류 응답

| 상태 코드 | 의미 |
|---|---|
| 400 Bad Request | 입력값 검증 실패 또는 비밀번호 불일치 등 |
| 404 Not Found | 존재하지 않는 일정 등 |

오류 응답 본문과 메시지는 실제 예외 처리 구현에 따라 달라질 수 있다.

## 5. 설계 자료

- **ERD:** Schedule 및 Comment 엔티티와 관계
- **MVC 흐름도:** Postman 요청부터 Controller, Service, Repository, 데이터베이스 및 응답까지의 처리 흐름


# ERD
![[내일배움캠프] 일정관리앱 ERD (1).jpg](../../Downloads/%5B%EB%82%B4%EC%9D%BC%EB%B0%B0%EC%9B%80%EC%BA%A0%ED%94%84%5D%20%EC%9D%BC%EC%A0%95%EA%B4%80%EB%A6%AC%EC%95%B1%20ERD%20%281%29.jpg)

# MVC 흐름도
![[내일배움캠프] 일정관리 MVC 흐름.jpg](../../Downloads/%5B%EB%82%B4%EC%9D%BC%EB%B0%B0%EC%9B%80%EC%BA%A0%ED%94%84%5D%20%EC%9D%BC%EC%A0%95%EA%B4%80%EB%A6%AC%20MVC%20%ED%9D%90%EB%A6%84.jpg)
