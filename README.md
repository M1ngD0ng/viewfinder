# ViewFinder
스냅 사진을 찍고 싶은 모델과 포토그래퍼를 매칭하는 서비스 

## ✔️ Team
<details>
<summary>김지수</summary>
<div markdown="1">
<ul>
  <li>회원가입/탈퇴</li>
</ul>
</div>
</details>

<details>
<summary>양소영</summary>
<div markdown="1">
<ul>
  <li>댓글 CRUD</li>
  <li>사용자 인증/인가</li>
</ul>
</div>
</details>

<details>
<summary>이민정</summary>
<div markdown="1">
<ul>
  <li>프로필 관리</li>
  <li>비밀번호 변경</li>
  <li>사용자 인증/인가</li>
</ul>
</div>
</details>

<details>
<summary>이시영</summary>
<div markdown="1">
<ul>
  <li>로그인/로그아웃</li>
  <li>코드 리팩토링</li>
  <li>사용자 인증/인가</li>
</ul>
</div>
</details>

<details>
<summary>이여재</summary>
<div markdown="1">
<ul>
  <li>구인 게시물 CRUD</li>
</ul>
</div>
</details>



## ✔️ Tech Stack
- 언어 : Java - JDK 17
- 프레임워크 : Spring Boot
- 데이터베이스 : Mysql

## ✔️ Features
- 사용자 인증 기능:
    - Spring Security와 JWT를 사용한 Access Token, Refresh Token 구현하였습니다.
    - 사용자 ID, 비밀번호를 입력하여 회원가입, 로그인할 수 있습니다.
    - 가입된 사용자의 회원 상태를 변경하여 탈퇴처리가 가능합니다.
    - 로그아웃 시, 발행한 토큰을 초기화합니다.
    - 이메일 가입 시 이메일 인증 기능을 포함합니다.
- 프로필 관리 기능:
    - 사용자 ID, 이름, 한 줄 소개, 이메일을 조회할 수 있습니다.
    - 로그인한 사용자는 본인의 사용자 정보(이름, 이메일, 한 줄 소개, 비밀번호)를 수정할 수 있습니다.
- 구인 게시물 CRUD 기능:
    - 모든 사용자가 전체 게시물을 조회할 수 있습니다.
        - 페이지네이션 기능을 구현하여 각 페이지 당 게시물 데이터를 10개씩 조회할 수 있습니다.
        - 생성일자 기준 최신순, 좋아요 많은 순으로 정렬할 수 있습니다.
        - 선택한 기간 별로 게시물을 조회할 수 있습니다.
    - 게시물의 작성, 수정, 삭제 기능은 인가가 필요합니다.
        - 자신이 작성한 뉴스피드만을 수정, 삭제할 수 있습니다.
- 댓글 CRUD 기능:
    - 사용자는 게시물에 댓글을 작성할 수 있고, 본인의 댓글은 수정 및 삭제를 할 수 있습니다.
    - 댓글의 작성, 수정, 삭제 기능은 인가가 필요합니다.
        - 댓글의 내용을 입력하여 작성할 수 있습니다.
        - 자신이 작성한 댓글만을 수정, 삭제할 수 있습니다.
- 좋아요 기능:
    - 사용자가 게시물이나 댓글에 좋아요를 남기거나 취소할 수 있으며, 본인이 작성하지 않은 게시물에만 좋아요를 누를 수 있습니다.
- 팔로우 기능:
    - 특정 사용자를 팔로우 / 언팔로우를 할 수 있습니다.
    - 뉴스피드에 팔로우하는 사용자의 게시물을 최신순으로 볼 수 있습니다.
## ✔️ 관련 자료
<details>
<summary>ERD Diagram</summary>
<div markdown="1">
  
![image](https://github.com/M1ngD0ng/viewfinder/assets/60657536/302ea990-8958-42fe-b05b-a4b7ed61c407)
  
</div>
</details>

<details>
<summary>API 명세서</summary>
<div markdown="1">

![image](https://github.com/M1ngD0ng/viewfinder/assets/60657536/d601d079-65c9-4b0b-a7d6-13573e986212)

</div>
</details>

## ✔️ Rules

### 브랜치명 규칙
- 브랜치명 = API 명세 기능명
    - feat : 새로운 기능 추가
    - fix : 코드 수정
    - refactor : 코드 리팩토링

### commit 규칙
| 작업 타입 | 작업내용 |
| --- | --- |
| ✨ update   | 해당 파일에 새로운 기능이 생김 |
| 🎉 add | 없던 파일을 생성함, 초기 세팅 |
| 🐛 bugfix | 버그 수정 |
| ♻️ refactor | 코드 리팩토링 |
| 🩹 fix | 코드 수정 |
| 🚚 move | 파일 옮김/정리 |
| 🔥 del | 기능/파일을 삭제 |
| 🍻 test | 테스트 코드를 작성 |
| 💄 style | css |
| 🙈 gitfix | gitignore 수정 |
| 🔨script | package.json 변경(npm 설치 등) |
