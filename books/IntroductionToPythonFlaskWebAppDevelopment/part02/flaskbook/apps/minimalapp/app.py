from flask import Flask, render_template, url_for, current_app, g, request, redirect, flash
from email_validator import validate_email, EmailNotValidError
import logging


# flask 클래스를 인스턴스화한다
app = Flask(__name__)

# flash 사용하기 위해 세션 config 로 세션을 설정(config는 앱을 이용하는 데 필요한 설정)
app.config["SECRET_KEY"] = "TEST"

app.logger.setLevel(logging.DEBUG)

# 애플리케이션 컨덱스트 취득하여 스택에 push 한다
ctx = app.app_context()
ctx.push()

# current_app에 접근할 수 있게 된다
print(current_app.name)
# >> apps.minimalapp.app

# 전역 임시 영역에 값을 설정한다
g.connection = "connection"
print(g.connection)
# >> connection

# URL과 실행할 함수를 매핑한다
@app.route("/")
def index():
    return "Hello, Flaskbook!"

# 허가할 HTTP 메서드를 GET과 POST로 하는 경우
@app.route("/hello/<name>", methods=["GET", "POST"], endpoint="hello-endpoint")
def hello(name):
    return "Hello, World! {name}"

# flask2부터 route 생략 가능
# @app.get("/hello")
# @app.post("/hello")
# def hello():
#     return "Hello, World!"

@app.route("/name/<name>")
def show_name(name):
    return render_template("index.html", name=name)

@app.route("/contact")
def contact():
    return render_template("contact.html")

@app.route("/contact/complete", methods=["GET", "POST"])
def contact_complete():
    if request.method == "POST":
        
        # form 속성을 사용해서 폼의 값을 취득한다
        username = request.form["username"]
        email = request.form["email"]
        description = request.form["description"]
        
        # 입력 체크
        is_valid = True
        
        if not username:
            flash("사용자명은 필수입니다.")
            is_valid = False
            
        if not email:
            flash("메일 주소는 필수입니다.")
            is_valid = False
            
        try:
            validate_email(email)
        except EmailNotValidError:
            flash("메일 주소의 형식으로 입력해 주세요.")
            is_valid = False
        
        if not description:
            flash("문의 내용은 필수입니다.")
            is_valid = False
        
        if not is_valid:
            return redirect(url_for("contact"))    
        
        # 이메일을 보낸다 <-- 더이상 지원하지 않습니다.
        
        flash("문의해 주셔서 감사합니다.")
        # contact 엔드포인트로 리다이렉트한다
        return redirect(url_for("contact_complete"))
    return render_template("contact_complete.html")



with app.test_request_context():
    # /
    print(url_for("index"))
    # /hello/world
    print(url_for("hello-endpoint", name="world"))
    # /name/AK?page=1
    print(url_for("show_name", name="AK", page="1"))
    
# 여기에서 호출하면 오류가 된다
# print(current_app)

with app.test_request_context("/users?updated=true"):
    #true 가 출력된다
    print(request.args.get("updated"))
    
    
app.logger.critical("fatal error")
app.logger.error("error")
app.logger.warning("warning")
app.logger.info("info")
app.logger.debug("debug")