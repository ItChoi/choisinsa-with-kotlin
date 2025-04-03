# 데이터베이스

---

## proxysql 접근
- master -> 쓰기만 담당
- slave -> 읽기만 담당
- 기준 데이터 -> read_only

## 마스터/슬레이브 구조
- master
  - root -> all privileges 권한 (DBA용) -> DDL 제어시 사용 할 계정
  - itchoi -> optional privileges 권한 (select, insert, update, delete) (개발자용)
  - repl_user -> replication slave 권한 (슬레이브 용도)
- slave
  - root -> all privileges 권한 (운영용) -> 건드릴 일 없음
  - itchoi -> optional privileges 권한 (오직 SELECT만!) (개발자용, 앱 -> slave read 요청만 수행)

---

## 테이블 설명
- 메뉴 권한 그룹 (menu_authority_group): 이 테이블을 기준으로 접근 가능한 실제 메뉴 데이터가 매핑되고, 관리자 인가 데이터로 활용된다.
- 회원 약관 동의 (member_terms_agreement): 회원이 동의한 약관, 약관 데이터 기준 유효한 약관인지 아닌지 파악이 필요하다.
- 약관 (member_terms): 회원 관련 약관 -> 회원이 이 약관을 동의한 데이터는 "회원 약관 동의 (member_terms_agreement)"에 쌓인다.

---

## 테이블, 테이블간 연관 관계설명
### 관리자 기준 연관 관계
- 관리자 (admin_member)
  - (1:N) 관리자 - 메뉴 권한 매핑 (admin_member_menu_authority)
    - (N:1) 메뉴 권한 그룹 (menu_authority_group)
      - (1:N) 권한 메뉴 매핑 (authority_menu)  

### 회원
- 회원 (member)
  - (1:N) 회원 인증 (member_certification)
  - (1:N) 회원 sns 연동 (member_sns_connect)
  - (1:N) 회원 약관 동의 (member_terms_agreement)
  - (1:N) 회원 배송지 (member_address) (추가 필요)
  - (1:N) 회원 사이즈 (member_size) (추가 필요)

### 추천


### 약관
- 회원 약관 (member_terms)
  - (1:N) 회원 약관 동의 (member_terms_agreement)

### 브랜드
- 회사 (company)
  - (1:N) 브랜드 (brand)
    - (1:1) 브랜드 상세 (brand_detail)
    - (1:N) 상품 (item)

### 상품
- 상품 카테고리 (item_category)
  - (1:N) 상품 카테고리 클로저 (item_category_closure)
    - id
    - ancestor
    - descendant
    - depth
    - display
  - (1:N) 상품 (item)
    - (1:N) 상품 이미지 (item_image)
      - (1:N) 상품 썸네일 (item_thumbnail)
    - (1:N) 상품 에디터 정보 (item_editor_info)
      - (1:N) 상품 에디터 내용 (item_editor_content)
        - (1:N) 상품 에디터 마크업 텍스트 (item_editor_markup_text)
        - (1:N) 상품 에디터 이미지 (item_editor_image)


---