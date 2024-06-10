from flask import *

from database import*

head=Blueprint('head',__name__)


@head.route('/head_home')
def head_home():
	return render_template('head_home.html')

@head.route('/head_addstudent',methods=['get','post'])
def head_addstudent():
	data={}
	q="select * from student order by student_id desc "
	res=select(q)
	data['staff']=res



	

	if "staff" in request.form:
		hid=session['head_id']
		f=request.form['fname']
		l=request.form['lname']
		pl=request.form['place']
		ph=request.form['ph']
		m=request.form['mail']
		

		
		
		q="insert into student values(null,'%s','%s','%s','%s','%s','%s')"%(hid,f,l,pl,ph,m)
		insert(q)
		flash('successfully')
		return redirect(url_for('head.head_addstudent'))
	return render_template('head_addstudent.html',data=data)


@head.route('/head_viewnotifiaction',methods=['post','get'])
def head_viewnotifiaction():
	data={}
	q="select * from notification order by notification_id desc"
	res=select(q)
	data['notifi']=res

	return render_template('head_viewnotifiaction.html',data=data)