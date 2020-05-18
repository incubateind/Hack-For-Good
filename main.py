from flask import Flask, render_template, request
app = Flask(__name__)
import pickle

#open a file, where you stored the data
file = open('model.pkl', 'rb')
mlf = pickle.load(file)
file.close()

@app.route('/', methods=["GET", "POST"])
def hello():
    if request.method == "POST":
        # print(request.form)
        myDict = request.form
        fever = int(myDict['fever'])
        age = int(myDict['age'])
        quarantine = int(myDict['quarantine'])
        tiredness = int(myDict['tiredness'])
        bodyPain = int(myDict['bodyPain'])
        runnynose = int(myDict['runnynose'])
        soreThroat = int(myDict['soreThroat'])
        diarrhoea = int(myDict['diarrhoea'])
        travelHistory = int(myDict['travelHistory'])
        diffbreath = int(myDict['diffbreath'])
        dryCough = int(myDict['dryCough'])


        #code for inference
        inputFeatures = [fever,age,quarantine,tiredness,bodyPain,runnynose,soreThroat,diarrhoea,travelHistory,diffbreath,dryCough]
        infP = mlf.predict_proba([inputFeatures])[0][1]
        print(infP)
    return render_template('index.html')
    #return "Hello, World!" + str(infP) 

if __name__ == "__main__":    
    app.run(debug=True)
