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
    df = pd.read_csv('dataSet.csv')
    train, test = data_split(df, 0.2)

    X_train = train[['fever', 'tiredness', 'dryCough', 'diffbreath', 'runnyNose', 'soreThroat', 'diarrhoea', 'travelHistory', 'isolationDays', 'age']].to_numpy()
    X_test = test[['fever', 'tiredness', 'dryCough', 'diffbreath', 'runnyNose', 'soreThroat', 'diarrhoea', 'travelHistory', 'isolationDays', 'age']].to_numpy()
   
    Y_train = train[['infection']].to_numpy().reshape(1600,)
    Y_test = test[['infection']].to_numpy().reshape(399,)
    mlf = LogisticRegression()
    mlf.fit(X_train, Y_train)

    # open a file, where you want to store a data
    file = open('model.pkl', 'wb')

    #dump information to that file
    pickle.dump(mlf, file)
    file.close
   