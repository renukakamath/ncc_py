from flask import *

from database import*

admin=Blueprint('admin',__name__)


@admin.route('/admin_home')
def admin_home():
	return render_template('admin_home.html')

@admin.route('/admin_addstaff',methods=['get','post'])
def admin_addstaff():
	data={}
	q="select * from staff inner join login using (login_id) where order by staff_id desc"
	res=select(q)
	data['staff']=res


	if "action" in request.args:
		action=request.args['action']
		sid=request.args['lid']


	else:
		action=None

	if  action=='update':
		q="select * from staff where login_id='%s'"%(sid)
		res=select(q)
		data['supdate']=res

	if "update" in request.form:
		f=request.form['fname']
		l=request.form['lname']
		pl=request.form['place']
		ph=request.form['ph']
		m=request.form['mail']
		d=request.form['desi']
		q="update staff set fname='%s',lname='%s',place='%s',phone='%s',email='%s',designation='%s' where login_id='%s'"%(f,l,pl,ph,m,d,sid)
		update(q)
		flash('successfully')
		return redirect(url_for('admin.admin_addstaff'))
			

		

	if action=='delete':
		q="delete from staff where login_id='%s'"%(sid)
		delete(q)
		q="delete from login where login_id='%s'"%(sid)
		delete(q)
		flash('successfully')
		return redirect(url_for('admin.admin_addstaff'))
	

	if "staff" in request.form:
		f=request.form['fname']
		l=request.form['lname']
		pl=request.form['place']
		ph=request.form['ph']
		m=request.form['mail']
		d=request.form['desi']
		u=request.form['uname']
		p=request.form['pass']
		q="insert into login values(null,'%s','%s','staff')"%(u,p)
		id=insert(q)
		q="insert into staff values(null,'%s','%s','%s','%s','%s','%s','%s')"%(id,f,l,pl,ph,m,d)
		insert(q)
		flash('successfully')
		return redirect(url_for('admin.admin_addstaff'))
	return render_template('admin_addstaff.html',data=data)

@admin.route('/admin_addhead',methods=['get','post'])
def admin_addhead():
	data={}

	q="select * from ncc_head inner join login using (login_id) where order by head_id desc"
	res=select(q)
	data['head']=res



	
	if "head" in request.form:
		f=request.form['fname']
		l=request.form['lname']
		pl=request.form['place']
		ph=request.form['ph']
		m=request.form['mail']

		u=request.form['uname']
		p=request.form['pass']
		q="insert into login values(null,'%s','%s','ncchead')"%(u,p)
		id=insert(q)
		q="insert into ncc_head values(null,'%s','%s','%s','%s','%s','%s')"%(id,f,l,pl,ph,m)
		insert(q)
		flash('successfully')
		return redirect(url_for('admin.admin_addhead'))

	if "action" in request.args:
		action=request.args['action']
		hid=request.args['lid']
	else:
		action=None

	if action=='update':

		q1="select * from ncc_head where login_id='%s'"%(hid)
		res1=select(q1)
		print(q1)
		data['nhead']=res1
	

	if "update" in request.form:
		f=request.form['fname']
		l=request.form['lname']
		pl=request.form['place']
		ph=request.form['ph']
		m=request.form['mail']
		
		q="update ncc_head set first_name='%s',last_name='%s',place='%s',phone='%s',email='%s' where login_id='%s'"%(f,l,pl,ph,m,hid)
		update(q)
		flash('successfully')
		return redirect(url_for('admin.admin_addhead'))



	if action=='delete':
		q="delete from ncc_head where login_id='%s'"%(hid)
		delete(q)
		q="delete from login where login_id='%s'"%(hid)
		delete(q)
		flash('successfully')
		return redirect(url_for('admin.admin_addhead'))	

	return render_template('admin_addhead.html',data=data)


@admin.route('/admin_viewactivities')
def admin_viewactivities():
	data={}
	q="select * from activities inner join ncc_head using (head_id)  order by activity_id desc"
	res=select(q)
	data['act']=res

	return render_template('admin_viewactivities.html',data=data)


@admin.route('/admin_viewattendance')
def admin_viewattendance():
	data={}
	q="SELECT * ,CONCAT(`student`.`first_name`) AS fname FROM `attendance` INNER JOIN `student` USING (student_id) INNER JOIN ncc_head ON ncc_head.head_id=attendance.head_id"
	res=select(q)
	data['att']=res

	return render_template('admin_viewattendance.html',data=data)	



@admin.route('/admin_viewcampreport')
def admin_viewcampreport():
	data={}
	q="SELECT *,concat(camp_details.place) as cplace FROM `camp_details` INNER JOIN `ncc_head` USING (head_id)  order by camp_id desc"
	res=select(q)
	data['camp']=res

	return render_template('admin_viewcampreport.html',data=data)		


@admin.route('/admin_viewexpenses')
def admin_viewexpenses():
	data={}
	q="SELECT * FROM `expences` INNER JOIN `ncc_head` USING (head_id)  order by expence_id desc"
	res=select(q)
	data['exp']=res

	return render_template('admin_viewexpenses.html',data=data)	

@admin.route('/admin_viewplan')	
def admin_viewplan():
	data={}
	q="select * from plan  order by plan_id desc"
	res=select(q)
	data['plan']=res
	return render_template('admin_viewplan.html',data=data)


@admin.route('/admin_viewstudent')
def admin_viewstudent():
	data={}
	q="SELECT *,CONCAT (ncc_head.first_name) AS fname ,CONCAT(student.first_name) as sname ,CONCAT(student.place) as splace ,concat (student.phone) as sphone, concat (student.email) as semail FROM student INNER JOIN ncc_head USING (head_id)"
	res=select(q)
	data['stud']=res
	return render_template('admin_viewstudent.html',data=data)


		
			





			
			
		
