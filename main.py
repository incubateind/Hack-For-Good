from flask import Flask, render_template,request
app = Flask(__name__)
import pickle

file = open('model.pkl', 'rb')
clf = pickle.load(file)
file.close()

@app.route('/', methods=["Get","POST"])
def hello_world():

    if request.method=="POST":
        val=request.form
        Fever=float(val['Fever'])
        Age=int(val['Age'])
        BodyPain=int(val['BodyPain'])
        RunnyNose=int(val['RunnyNose'])
        DiffBreath=int(val['DiffBreath'])

        InputFeature = [Fever, BodyPain, Age, RunnyNose, DiffBreath]
        infproba = clf.predict_proba([InputFeature])[0][1]
        print(infproba)
        return render_template('show.html', inf = round(infproba*100))
    return render_template('index.html')
        # return 'hello, world' + str(infproba)

if __name__ == "__main__":
    app.run(debug=True)

