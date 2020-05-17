import pandas as pd
import numpy as np
from sklearn.linear_model import LogisticRegression
import pickle

def data_split(data, ratio):
    np.random.seed(42)
    shuffled = np.random.permutation(len(data))
    test_set_size = int(len(data) * ratio)
    test_indices = shuffled[:test_set_size]
    train_indices = shuffled[test_set_size:]
    return data.iloc[train_indices], data.iloc[test_indices]

if __name__ == "__main__":
    df = pd.read_csv("data.csv")
    train, test = data_split(df, 0.2)
    x_train = train[["Fever", "BodyPain", "Age", "RunnyNose", "DiffBreath"]].to_numpy()
    x_test = test[["Fever", "BodyPain", "Age", "RunnyNose", "DiffBreath"]].to_numpy()
    
    y_train = train[["InfectionProb"]].to_numpy().reshape(1616,)
    y_test = test[["InfectionProb"]].to_numpy().reshape(404,)

    clf = LogisticRegression()
    clf.fit(x_train, y_train)


    file = open('model.pkl', 'wb')
    pickle.dump(clf, file)
    file.close()

    