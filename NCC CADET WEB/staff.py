from flask import*

from database import*

staff=Blueprint('staff',__name__)


@staff.route('/staff_home')
def staff_home():
	return render_template('staff_home.html')
@staff.route('/staff_updatestudent',methods=['post','get'])
def staff_updatestudent():
	data={}
	q="select * from student order by student_id desc"
	res=select(q)
	data['student']=res

	if "action" in request.args:
		action=request.args['action']
		sid=request.args['sid']


		if action=='update':
			q="select * from student"
			res=select(q)
			data['supdate']=res
		if "update" in request.form:
			f=request.form['sname']
			l=request.form['lname']
			p=request.form['place']
			ph=request.form['phone']
			e=request.form['email']
			q="update student set first_name='%s',last_name='%s',place='%s',phone='%s',email='%s' where student_id='%s'"%(f,l,p,ph,e,sid)
			update(q)
			return redirect(url_for('staff.staff_updatestudent'))
		

	
	return render_template('staff_updatestudent.html',data=data)


@staff.route('/staff_academicdetails',methods=['post','get'])
def staff_academicdetails():
	data={}
	q="select * from student"
	res=select(q)
	data['stu']=res


	q="select * from academic inner join student using (student_id) order by academic_id desc"
	res=select(q)
	data['accad']=res

	if "academic" in request.form:
		s=request.form['student']
		a=request.form['acc']
		d=request.form['det']
		p=request.form['ph']
		m=request.form['mail']
		q="insert into academic values(null,'%s','%s','%s','%s','%s')"%(s,a,d,p,m)
		insert(q)
		flash('successfully')
		return redirect(url_for('staff.staff_academicdetails'))

	return render_template('staff_academicdetails.html',data=data)

@staff.route('/staff_addnotification',methods=['post','get'])
def staff_addnotification():
	data={}
	q="select * from notification order by notification_id desc"
	res=select(q)
	data['notifi']=res

	if "Notification" in request.form:
		n=request.form['not']
		d=request.form['det']
		q="insert into notification values(null,'%s','%s',curdate())"%(n,d)
		insert(q)
		flash('successfully')
		
		return redirect(url_for('staff.staff_addnotification'))
	return render_template('staff_addnotification.html',data=data)
		
			
			