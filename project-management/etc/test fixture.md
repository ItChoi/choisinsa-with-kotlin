# Test Fixture

## 구조 정하기
- 구조 지정 필요
  1. ~~enum 관리~~
  2. ~~database 데이터 관리~~
  3. ~~dto 관리~~
  4. ~~entity class 관리~~
  5. ~~fixture 모듈 관리~~
  6. ~~core 모듈 - fixture 패키지 별도 분리 관리~~
  7. ~~테스트 클래스 내 관리 - @BeforeAll, @BeforeEach 등으로 관리~~
  8. ~~테스트 클래스 내 관리 - test fixture 메서드 추가로 관리~~
  9. ~~test fixture 외부 라이브러리 사용~~ 
  10. **kotlin - test fixture 클래스 추가 -> builder / dsl 활용 (채택)**

## 구조 장, 단점 
1. enum 관리
   - 장점
     - 완성된 객체로 관리 가능
     - 객체간 연관 관계 매핑 수월
     - 특정 언어에 의존도가 높지 않음
     - 정적 데이터로 한 번 세팅된 데이터 공유 가능
     - dto 별개로 완성된 형태로 관리 (장점이자 단점 - 관리 포인트 증가)
   - 단점
     - 테이블 수정에 대한 관리 포인트 증가
     - 완성형 데이터 만드는 비용 발생
     - 실제 테스트 유효성과 다른 결과 (테스트로 인지 할 수 있어야 하는 것을 놓칠 수 있다.)
2. ~~database 데이터 관리~~
   - 장점
     - 활용성이 낮지만 데이터 제어 가능
   - 단점
     - 장점 뺴고 모두
3. ~~dto 관리~~
   - 장점
     - 따로 test fixture를 위한 클래스를 만들 필요 없음
   - 단점
     - 비슷한 dto에 비슷한 test fixture를 추가하는 번거로움이 있을 수 있음
     - 원하는 test fixture dto 서칭 비용이 있거나 잘못 추가되는 경우가 있을 수 있음
4. ~~entity class 관리~~
   - 장점
     - test fixture 서칭 쉬움
   - 단점
     - 엔티티 클래스에 테스트 코드 의존성
     - 엔티티 외 코드가 길어짐
     - 엔티티와 관련된 dto를 모두 알고 있어야 함
5. ~~fixture 모듈 관리~~
   - 장점
     - test fixture 모듈로 의존성 주입만으로 쉽게 사용 가능
     - 언어 또는 DB 벤더에 따라 맞춤형으로 test fixture 제공 가능
   - 단점
     - 사용하지 않는 모든 test fixtrue를 알고 있음
     - 만들기 번거로움
6. core 모듈 - fixture 패키지 별도 분리 관리
   - 장점
     - 완성된 객체로 관리 가능    
     - 객체간 연관 관계 매핑 수월
     - 특정 언어에 의존도가 높지 않음
     - 기존 dto 별개로 완성된 형태로 관리 (장점이자 단점 - 관리 포인트 증가)
     - core 모듈을 사용하는 모듈에서 공통 사용 가능
     - enum 관리와 유사한 형태 -> fixture 데이터는 완성형 데이터로 연관관계까지 매핑
   - 단점
     - enum 단점 동일
7. ~~테스트 클래스 내 관리 - @BeforeAll, @BeforeEach 등으로 관리~~
   - 장점
     - 사용하는 곳에 원하는 test fixture 자유롭게 추가 가능
     - 테스트 클래스 내 필요로 하는 test fixtrue 제어 가능
   - 단점 
     - 테스트 클래스 별 중복 생성 해야 되는 경우가 생김
     - 테스트 클래스 내 라인이 길어질 수도 있음
     - 일부 테스트 코드에는 적합하지만, 공유 자원으로 사용돼다가 test fixtrue 변경으로 인한 에러 발생 가능
8. ~~테스트 클래스 내 관리 - test fixture 메서드 추가로 관리~~
   - 7과 거의 유사
9. ~~test fixture 외부 라이브러리 사용~~
   - 이번에는 외부 라이브러리 사용은 제외, 우선 내부에서 관리하는 test fixtrue 경험하기.
10. **kotlin - test fixture 클래스 추가 -> builder / dsl 활용 (채택)**
   - 장점 
     - 코틀린 활용 가독성 좋은 코드로 생성 가능
     - 코틀린 활용 디폴트 값 + 수정할 값 제어 가능
     - 엔티티, dto 분리하여 관리 상대적으로 수월
   - 단점
     - 코틀린에 종속적
     - 팀 개발자가 원활하게 관리하기 위해 규칙 필요

## Test Fixture 규칙
1. 엔티티, dto fixture 분리한다.
2. fixture 네이밍
   - 엔티티: entity + fixture (MemberFixture)
   - dto
     - request: entity + request + fixture (MemberRequestFixture)
     - response: entity + response + fixture (MemberResponseFixture)
3. kotlin builder + dsl 코드를 활용한다.
    ```kotlin
    // TODO: 픽스쳐 클래스 
    class MemberFixture {
        var id: Long? = null
        var name: String = "Alice"
        var email: String = "alice@example.com"
        var age: Int = 20
    
        fun build(): Member {
            return Member(id, name, email, age)
        }
    }
    
    // DSL 함수: 원하는 필드만 손쉽게 변경 가능
    fun member(block: MemberFixture.() -> Unit = {}): Member {
        val builder = MemberFixture()
        builder.apply(block)
        return builder.build()
    }
    
    //-------------------------------------
    
    // TODO: 사용 로직
    class MemberServiceTest {
    
        @Test
        fun 기본_유저_생성() {
            val defaultMember = member()
            // name="Alice", email="alice@example.com", age=20
            // 테스트 로직 ...
        }
    
        @Test
        fun 커스텀_값으로_유저_생성() {
            val customMember = member {
                name = "Bob"
                age = 30
            }
            // 테스트 로직 ...
        }
    }
    ```