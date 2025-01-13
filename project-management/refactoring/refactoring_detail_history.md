# member 테이블 분리 (member, admin_member) (25.01.13)
- 테이블 연관 관계 수정 -> member 테이블로 일반 회원, 관리자 -> admin_member 테이블 추가로 일반 회원과 관리자 데이터 분리
    - member 데이터가 관리자인 경우 매핑되어 있던 테이블 수정 (member_authority, admin_member_company)
    - company -> fk로 ~~member_id~~ -> admin_member_id로 변경