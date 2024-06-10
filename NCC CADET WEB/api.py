from flask import*
from database import*

api=Blueprint('api',__name__)

@api.route('/logins')
def logins():
	data={}

	u=request.args['username']
	p=request.args['password']
	q="select * from login where user_name='%s' and password='%s'"%(u,p)
	res=select(q)
	if res:
		data['data']=res
		data['status']='success'
	return str(data)

@api.route('/Addstudents')
def Addstudents():
	data={}
	login_id=request.args['login_id']
	f=request.args['first_name']
	l=request.args['last_name']
	p=request.args['place']
	ph=request.args['phone']
	e=request.args['email']
	q="insert into student values(null,(select  head_id from ncc_head where login_id='%s'),'%s','%s','%s','%s','%s')"%(login_id,f,l,p,ph,e)
	insert(q)
	
	data['status']='success'
	data['method']='Addstudent'
	return str(data)

@api.route('/ViewStudent')	
def ViewStudent():
	data={}
	login_id=request.args['login_id']
	q="select * from student where head_id=(select head_id from ncc_head where login_id='%s')"%(login_id)
	res=select(q)
	print(q)
	
	if res:
		data['data']=res
		data['status']='success'
	data['method']='ViewStudent'
	return str(data)


@api.route('/Viewnotification')
def Viewnotification():
	data={}
	q="select * from notification"
	res=select(q)
	if res:
		data['data']=res
		data['status']='success'
	return str(data)

@api.route('/Addplan')
def Addplan():
	data={}
	p=request.args['plan']
	d=request.args['details']
	pf=request.args['planfor']
	q="insert into plan values(null,'%s','%s','%s')"%(p,d,pf)
	insert(q)
	data['status']='success'
	data['method']='Addplan'
	return str(data)

@api.route('/Viewplan')	
def Viewplan():
	data={}
	q="select * from plan"
	res=select(q)
	if res:
		data['data']=res
		data['status']='success'
	data['method']='Viewplan'
	return str(data)

@api.route('/AddCamp')	
def AddCamp():
	data={}
	c=request.args['camp']
	d=request.args['details']
	p=request.args['place']
	f=request.args['fromdate']
	t=request.args['todate']
	l=request.args['login_id']
	q="insert into camp_details values(null,(select head_id from ncc_head where login_id='%s'),'%s','%s','%s','%s','%s')"%(l,c,d,p,f,t)
	insert(q)
	print(q)
	data['status']='success'
	data['method']='AddCamp'
	return str(data)

@api.route('/Viewcamp')
def Viewcamp():
	data={}
	l=request.args['login_id']
	q="select * from camp_details where head_id=(select head_id from ncc_head where login_id='%s')"%(l)
	res=select(q)
	if res:
		data['data']=res
		data['status']='success'
		data['method']='Viewcamp'
	return str(data)

@api.route('/Addactivity')
def Addactivity():
	data={}
	l=request.args['login_id']
	a=request.args['Activity']
	d=request.args['details']
	
	q="insert into activities values(null,(select head_id from ncc_head where login_id='%s'),'%s','%s',curdate()) "%(l,a,d)
	insert(q)
	data['status']='success'
	data['method']='Addactivity'
	return str(data)

@api.route('/Viewactivity')	
def Viewactivity():
	data={}
	l=request.args['login_id']
	q="select * from activities where head_id=(select head_id from ncc_head where login_id='%s')"%(l)
	res=select(q)
	if res:
		
		data['data']=res
		data['status']='success'
		data['method']='Viewactivity'
	return str(data)


@api.route('/viewproductspinner')
def viewproductspinner():
	data={}
	q="select * from student"
	res=select(q)
	if res:
		data['data']=res
		data['status']='success'
		data['method']='viewproductspinner'
		return str(data)


@api.route('/Addattendance')
def Addattendance():
	data={}
	l=request.args['login_id']
	s=request.args['sid']

	a=request.args['attendance']

	q="insert into attendance values(null,'%s',(select head_id from ncc_head where login_id='%s'),curdate(),'%s')"%(s,l,a)
	insert(q)
	data['status']='success'
	data['method']='Addattendance'
	return str(data)


@api.route('/Viewattendance')	
def Viewattendance():
	data={}
	l=request.args['login_id']
	q="select * from attendance inner join student using (student_id) where attendance.head_id=(select head_id from ncc_head where login_id='%s')"%(l)
	res=select(q)
	print(q)
	if res:
		data['data']=res
		data['status']='success'
		data['method']='Viewattendance'
	return str(data)


@api.route('/Viewexpence')	
def Viewexpence():
	data={}
	l=request.args['login_id']
	q="select * from expences where head_id=(select head_id from ncc_head where login_id='%s')"%(l)
	res=select(q)
	if res:
		data['data']=res
		data['status']='success'
		data['method']='Viewexpence'
	return str(data)

@api.route('/Addexpence')
def Addexpence():
	data={}
	l=request.args['login_id']
	e=request.args['expence']
	d=request.args['details']
	
	q="insert into expences values(null,(select head_id from ncc_head where login_id='%s'),'%s','%s',curdate())"%(l,e,d)
	insert(q)
	data['status']='success'
	data['method']='Addexpence'	
	return str(data)

@api.route('/updatestudents')
def updatestudents():
	data={}
	sid=request.args['sid']
	login_id=request.args['login_id']
	f=request.args['first_name']
	l=request.args['last_name']
	p=request.args['place']
	ph=request.args['phone']
	e=request.args['email']
	q="update student set first_name='%s' ,last_name='%s' ,place='%s',phone='%s',email='%s' where student_id='%s'"%(f,l,p,ph,e,sid)
	update(q)
	print(q)
	
	data['status']='success'
	data['method']='Addstudent'
	return str(data)



									
		
			
				


	


	

		
	
	

	

	