 # Snakes And Ladders
 
 ## 요구사항
 ![](snakesandladdersboard.jpg)
 
* 규칙
    1. 두 플레이어는 0의 위치에서 시작합니다.
    2. 플레이어 1이 먼저 시작하고, 다음에 플레이어 2가 교대합니다.
    3. 1부터 100까지 순서대로 움직입니다.
    4. 주사위 눈이 같으면 같은 플레이어가 한번 더 플레이합니다.
    5. 사자리 아래의 칸으로 이동한 경우, 사다리 위로 이동합니다.
    6. 뱀 머리의 칸으로 이동한 경우, 뱀 꼬리 칸으로 내려옵니다.
    7. 마지막 칸에 정확히 도착해야 이깁니다. 그 이상으로 간 경우 되돌아 갑니다. 
    예를 들어 98에 있는데 5가 나오면, 97로 돌아옵니다. (99, 100, 99, 98, 97)
    
## 통합테스트 실행 방법
```
git clone https://github.com/voyagerwoo/snakes-ladders
cd snakes-ladders
./mvnw integration-test
```