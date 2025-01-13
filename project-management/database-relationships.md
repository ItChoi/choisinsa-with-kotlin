# 관리자, 권한 연관 관계
- 관리자 (admin_member)
  - (1:N) 관리자 - 메뉴 권한 매핑 (admin_member_menu_authority)
    - (N:1) 메뉴 권한 그룹 (menu_authority_group)
      - (1:N) 권한 메뉴 매핑 (authority_menu)  
