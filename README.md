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


4. 주문 도메인 개발
   1. Order 비즈니스 로직 추가
   2. OrderRepository
   3. OrderService

5. API 개발 기본
    1. 회원 등록 API
    2. 회원 수정 API
    3. 회원 조회 API

    

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
   

22.03.08

정리 : [API 기본](https://jddng.tistory.com/330)

1. api 기본(JSON 바인딩) - MemberApiController
   1. 회원 등록 API 
   2. 회원 수정 API
   3. 회원 조회 API

22.03.09

정리 : [API 개발 고급 - 조회 성능 최적화](https://jddng.tistory.com/332)

1. api ManyToOne, OneToOne 조회 성능 최적화
   1. 엔티티를 DTO로 변환하는 방법
   2. 페치 조인으로 성능을 최적화 하는 방법
   3. DTO로 직접 조최하는 방법



22.03.12

1. api Collection 조회 최적화
   1. 페치 조인 최적화
   2. 페이징과 한계
   3. DTO로 직접 조회


---


## 복습

22.03.13

- 도메인 설계 복습

22.03.15

- practice
  - 도메인 설계 복습
  - 회원 Repository, Service 복습
  - 회원 기능 단위 테스트
  - 상품 Repository, Service 복습
  - 상품 기능 단위 테스트

22.03.16

- practice2
  - 도메인 설계 복습
    - ManyToMany -> OneToMany ManytoOne 관계로 변경

22.03.17

- practice2
  - 회원 Repository, Service 복습
  - 상품 Repository, Service 복습
  - 주문 Repository, Service 복습

22.03.18

- practice2
  - 웹 계층 개발 복습

22.03.19

- practice2 리펙토링
  - setter 삭제
  - Category, Item 연관관계 설정 및 테스트
  - Order 테스트

22.03.19

- practice2
  - OrderService 검색 기능 추가
  - MemberService 테스트
  - OrderService 테스트

22.03.22

- practice2
  - API 개발 고급
    - 엔티티를 DTO로 변환(페치 조인 최적화)
    - JPA에서 DTO로 바로 조회하는 방법

  

