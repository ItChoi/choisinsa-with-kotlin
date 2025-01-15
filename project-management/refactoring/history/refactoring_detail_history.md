# member 테이블 약관 동의 칼럼 -> 약관 테이블로 분리 (25.01.15)
- as-is
  - member.member_detail 테이블에서 회원별 약관 동의 여부 제어
    - member.member_detail.is_accept_marketing
    - member.member_detail.is_authenticate_email
    - member.member_detail.is_authenticate_phone
- to-be
  - 회원 약관 (member_terms)
    - 회원 관련 약관 데이터 관리
  - 회원 약관 동의 (member_terms_agreement)
    - 회원이 실제 동의한 약관 데이터 (물리 데이터로 관리, 수락 후 미수락 변경시 데이터 삭제)

---

# 회원 테이블 분리 (member, admin_member) (25.01.13)
- 테이블 연관 관계 수정 -> member 테이블로 일반 회원, 관리자 -> admin_member 테이블 추가로 일반 회원과 관리자 데이터 분리
    - member 데이터가 관리자인 경우 매핑되어 있던 테이블 수정 (member_authority, admin_member_company)
    - company -> fk로 ~~member_id~~ -> admin_member_id로 변경