from flask import Flask 

from public import public
from admin import admin
from staff import staff
from head import head
from api import api


app=Flask(__name__)

app.secret_key='key'


app.register_blueprint(public)
app.register_blueprint(admin)
app.register_blueprint(staff)
app.register_blueprint(head)
app.register_blueprint(api,url_prefix='/api')

app.run(debug=True,port=5007,host="0.0.0.0")