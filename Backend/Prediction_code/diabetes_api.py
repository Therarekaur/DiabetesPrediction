# -*- coding: utf-8 -*-
# """
# Spyder Editor

# This is a temporary script file.
# """

from fastapi import FastAPI
from pydantic import BaseModel
import pickle
import json
from fastapi.responses import JSONResponse


app = FastAPI()

class model_input(BaseModel):

    Glucose: str
    Insulin: str
    BMI: str
    DiabetesPedigreeFunction: str
    Age: str

# loading the saved model
diabetes_model = pickle.load(open('diabetes_model1.sav', 'rb'))


@app.post('/diabetes_prediction')
def diabetes_pred(input_parameters: model_input):
   
    input_data = input_parameters.model_dump_json()

    input_dictionary = json.loads(input_data)
    glu = int(input_dictionary['Glucose'])
    insulin = int(input_dictionary['Insulin'])
    bmi = float(input_dictionary['BMI'])
    dpf = float(input_dictionary['DiabetesPedigreeFunction'])
    age = int(input_dictionary['Age'])   
     
    input_list = [glu, insulin, bmi, dpf, age]
    prediction=diabetes_model.predict([input_list])
    if prediction[0] == 0:
        response_data = {
        "message": "Congratulations you don't have diabetes"
    }
        return JSONResponse(content=response_data)
    else:
        response_data = {
        "message": "You have diabetes"
        }
        return JSONResponse(content=response_data)
