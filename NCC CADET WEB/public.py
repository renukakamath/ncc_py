from flask import*

from database import*

public=Blueprint('public',__name__)


@public.route('/')
def home():
	return render_template('home.html')

@public.route('/login',methods=['post','get'])	
def login():

	if "login" in request.form:
		u=request.form['uname']
		p=request.form['pass']
		q="select * from login where user_name='%s' and password='%s'"%(u,p)
		res=select(q)
		if res:
			session['login_id']=res[0]['login_id']
			lid=session['login_id']

			if res[0]['user_type']=="admin":
				return redirect(url_for('admin.admin_home'))

			elif res[0]['user_type']=="staff":
				return redirect(url_for('staff.staff_home'))
			elif res[0]['user_type']=="ncchead":
				q="select * from ncc_head where login_id='%s'"%(lid)
				res=select(q)
				if res:
					session['head_id']=res[0]['head_id']
					hid=session['head_id']
					

				return redirect(url_for('head.head_home'))


		else:
			flash('invalid username and password')
	

	return render_template('login.html')

	
	

