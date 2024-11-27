## API Docs
## User
회원가입없이 토큰만 저장
<br>
### Request
#### End-point : http://url/user
#### Method : Post
#### body : 
{
  "token" : String
}
### Response
{
  "status": 201,
  "message": "성공"
}
## File
S3파일 저장 및 DB저장
### Request
#### End-point : http://url/file
#### Method : Post
#### form-data : files (Type = File)
### Response
{
  "status": 200,
  "message": "성공",
  [
    "id" : Int,
    "url" : String
  ]
}
## Board
작품 게시 및 조회
### Request
#### End-point : http://url/board
#### Method : Post
#### body :
{
  "title" : String,
  "content" : String, //nullable
  "files" : [Int] //nullabe, 파일 아이디
}
### Response
{
  "status": 200,
  "message": "성공"
}
### Request
#### End-point : http://url/board
#### Method : Get
#### body : 없음
### Response
{
  "status": 200,
  "message": "성공",
  "data": [
    {
      "id": Long,
      "title": String,
      "content": String,
      "imageUrl": [
        {
          "id" : Long,
          "url" : String
        }
      ]
    }
}

