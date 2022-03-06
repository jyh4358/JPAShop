# Spring Boot와 JPA



## 구현 내용

22.03.05

1. 도메인 설계
    1. 엔티티 클래스
    2. 연관관계 매핑
    3. 엔티티 설계시 주의점
    
    
2. 회원 도메인 개발
   1. MemberRepository
   2. MemberService
   3. 단위 테스트


3. 상품 도메인 개발
   1. Item 비즈니스 로직 추가
   2. ItemRepository
   3. ItemService

22.03.06

1. 주문 도메인 개발
   1. Order 비즈니스 로직 추가
   2. OrderRepository
   3. OrderService
   4. OrderSearch : 검색 기능에 필요한 클래스
   5. 단위 테스트
   

2. 웹 게층 개발
   1. 홈 화면과 레이아웃
   2. 회원 등록
   3. 회원 목록 조회
   4. 상품 등록
   5. 상품 목록
   6. 상품 수정
   7. 상품 주문, 검색, 취소