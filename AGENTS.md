# Repository 가이드라인

AGENTS.md는 한글 버전으로 유지하고 싶어.
프로젝트 문서 기본 언어는 한국어야.
새로 만드는 가이드/문서는 기본적으로 한국어로 작성하고,
영문이 필요하면 *.en.md 파일로 별도 생성해줘.

- 현재 AGENTS.md 내용을 자연스러운 한국어로 번역해서 AGENTS.md에 반영해줘.
- 원문 영어는 AGENTS.en.md로 새 파일로 만들어서 그대로 보관해줘.
- 마크다운 구조/헤더/코드블록은 유지해줘.
- .env 예시는 실제 시크릿이 아니니 그대로 두되, “샘플 값”임을 명확히 표시해줘.


## 프로젝트 구조 & 모듈 구성
- 멀티 모듈 Gradle 프로젝트이며, 모듈은 레포 루트에 있고 module-*-kt/ 규칙을 따릅니다.
- 애플리케이션 모듈:
  - module-app-choisinsa-kt/ (클라이언트 API)
  - module-app-admin-kt/ (어드민 API)
- 공용(Shared) 모듈: module-core-kt/, module-common-kt/, module-auth-kt/, module-redis-kt/
- 소스 코드는 src/main/kotlin/, 리소스는 src/main/resources/, 테스트는 src/test/kotlin/ 아래에 위치합니다.
- 인프라/설정 관련 파일은 docker-compose.yml, conf/, scripts/, sql/ 등에 있습니다.

## 빌드 / 테스트 / 개발 명령어
- ./gradlew build — 전체 모듈 컴파일 및 테스트 실행
- ./gradlew test — 전체 테스트 실행 (JUnit 5)
- ./gradlew :module-app-choisinsa-kt:bootRun — 클라이언트 API 로컬 실행 (com.mall.choisinsa.ChoisinsaApplication)
- ./gradlew :module-app-admin-kt:bootRun — 어드민 API 로컬 실행 (com.mall.choisinsa.AdminApplication)
- ./gradlew :module-app-choisinsa-kt:asciidoctor — REST Docs 생성 (module-app-choisinsa-kt/build/docs/asciidoc에 출력)
- docker-compose up — 로컬 인프라(MySQL, Redis, ProxySQL, Prometheus, Grafana) 실행. .env 필요

## 코딩 스타일 & 네이밍 규칙
- Kotlin + Spring Boot (Java toolchain 17). 표준 Kotlin 스타일을 따릅니다.
- 들여쓰기: 스페이스 4칸(탭 금지). 파일의 개행 규칙을 유지합니다.
- 네이밍:
  - 클래스: PascalCase
  - 함수/변수: camelCase
  - 상수: UPPER_SNAKE_CASE
- 패키지명은 소문자 점(.) 구분 형태를 사용합니다. (예: com.choisinsa.member)

## 테스트 가이드
- 사용 프레임워크: JUnit 5, Spring Boot Test, Spring REST Docs
- 테스트 파일명은 *Test.kt, 경로는 src/test/kotlin/ 아래에 둡니다.
- REST Docs 스니펫은 테스트 실행 시 생성되며, Asciidoctor 태스크에서 이를 입력으로 사용합니다.

## 커밋 & PR 가이드
- 커밋 메시지는 설명형 문장 형태를 권장합니다(한국어 메시지가 많음). 엄격한 포맷은 강제하지 않습니다.
- 변경 범위가 드러나는 간결한 요약을 권장합니다. 예: 회원 조회 테스트 추가
- PR에는 다음을 포함해주세요:
  - 목적
  - 주요 변경 사항
  - 테스트 결과
  - 관련 스크린샷/로그(필요 시)

## 로컬 셋업 (.env)
레포 루트에 .env 파일을 생성하여 Docker Compose에서 사용할 값을 설정합니다.
- 필수 키 (docker-compose.yml 기준):
  - DEFAULT_PASSWORD, MYSQL_DATABASE, MYSQL_MASTER_PORT, MYSQL_SLAVE_1_PORT, MYSQL_SLAVE_2_PORT, MYSQL_CLIENT_PORT, REDIS_CLIENT_PORT, REDIS_SERVER_PORT, GF_ADMIN_USER
- 예시 (샘플 값이며 실제 운영 시크릿을 넣지 마세요):
```ini
DEFAULT_PASSWORD=devpassword
MYSQL_DATABASE=choisinsa
MYSQL_MASTER_PORT=3306
MYSQL_SLAVE_1_PORT=3307
MYSQL_SLAVE_2_PORT=3308
MYSQL_CLIENT_PORT=3306
REDIS_CLIENT_PORT=6379
REDIS_SERVER_PORT=6379
GF_ADMIN_USER=admin
```

## 보안 & 설정 주의사항
- 로컬 인프라는 .env 값(포트/패스워드 등)에 의존합니다. 개발용 샘플 값만 사용하세요.
- Docker volume/network가 external: true로 설정되어 있다면, docker-compose up 전에 해당 리소스가 존재해야 합니다.