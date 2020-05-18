from flask import Flask, render_template, request
from flask_sqlalchemy import SQLAlchemy

app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql://root:@localhost/korona'
db = SQLAlchemy(app)

class Users(db.Model):
    sno = db.Column(db.Integer, primary_key=True)
    username = db.Column(db.String(20), nullable=False)
    password = db.Column(db.String(20), nullable=False)

@app.route("/")
def index():
    return render_template('home.html')

@app.route("/register")
def register():
    return render_template('register.html')

@app.route("/login_page")
def login_page():
    return render_template('login.html')

@app.route("/productpage1")
def productpage1():
    return render_template('productpage1.html')

@app.route("/productpage2")
def productpage2():
    return render_template('productpage2.html')

@app.route("/productpage3")
def productpage3():
    return render_template('productpage3.html')

@app.route("/productpage4")
def productpage4():
    return render_template('productpage4.html')

@app.route("/productpage5")
def productpage5():
    return render_template('productpage5.html')

@app.route("/productpage6")
def productpage6():
    return render_template('productpage6.html')

@app.route("/productpage7")
def productpage7():
    return render_template('productpage7.html')

@app.route("/productpage8")
def productpage8():
    return render_template('productpage8.html')

@app.route("/productpage9")
def productpage9():
    return render_template('productpage9.html')

@app.route("/ask_for_help")
def ask_for_help():
    return render_template('ask_for_help.html')

@app.route("/login", methods = ['GET', 'POST'])
def sign_in():
    if(request.method == 'POST'):
        enter_user = request.form.get('user')
        enter_pass = request.form.get('pass')
        manna = Users.query.filter_by(username=enter_user).first()

    if (manna.password == enter_pass):
        print("CHECKING SUCCESFUL")
    else:
        print("WRONG PASSWORD ENTERED")

    return render_template("dashboard.html")

@app.route("/dashboard")
def dashboard():
    return render_template("dashboard.html")

@app.route("/upgrade")
def upgrade():
    return render_template("upgrade.html")

@app.route("/map")
def map():
    return render_template("map.html")


@app.route("/tables")
def tables():
    return render_template("tables.html")

@app.route("/user")
def user():
    return render_template("user.html")

@app.route("/payment")
def payment():
    return render_template('payment.html')




app.run(debug=True)