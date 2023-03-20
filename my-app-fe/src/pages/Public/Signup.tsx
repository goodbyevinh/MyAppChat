import React, { useState, ChangeEvent } from 'react'
import config from './../../config/index';
import { useDispatch, useSelector } from 'react-redux';
import {uploadImage} from '../../services'
import { signup } from '../../store/account/action';
import { selectorLoadingAccount } from './../../store/account/selector';
import { alertError } from './../../store/alert/action';
import { selectorAlert } from './../../store/alert/selector';

type TypeForm = {
  email: string | null;
  fullname: string | null;
  password: string | null;
  repassword?: string | null;
  avatar: File | null
}

const Signup = () => {

  const [input , SetInput] = useState<TypeForm> ( {
    email: null,
    fullname: null,
    password: null,
    repassword: null,
    avatar: null
  });
  const [submmited, setSubmitted] = useState(false)
  
  const dispatch = useDispatch();
  const loading = useSelector(selectorLoadingAccount)
  const handleInputChange = (e: ChangeEvent<HTMLInputElement>)  => {
    const { name, value } = e.target
    SetInput({
        ...input,
        [name]: value
    })
  }

  const handleUploadImage = async(e: ChangeEvent<HTMLInputElement>) => {
    const file : any = e.target.files?.item(0)
    SetInput({
      ...input,
      avatar: file
    })
    //const data = await uploadImage(file);
    //SetAvatar(data)
  }

  const handleSubmmit = (e : ChangeEvent<HTMLFormElement>) => {
    e.preventDefault();
    const { email, password,repassword, fullname, avatar } = input

    setSubmitted(true);
    if (password !== repassword) {
      dispatch(alertError('Mật khẩu không giống'))
      return
    }

    if (email && password && fullname && avatar) {
      const form = config.path.home
    
        dispatch(signup(email, password, fullname, avatar, form))
      
    }

  }
  const alert = useSelector(selectorAlert)

  return (
    <div className="container">
      {/* Outer Row */}
      <div className="row justify-content-center">
        <div className="col-xl-10 col-lg-12 col-md-9">
          <div className="card o-hidden border-0 shadow-lg my-5">
            <div className="card-body p-0">
              {/* Nested Row within Card Body */}
              <div className="row justify-content-center">
                <div className="col-lg-6">
                  <div className="p-5">
                    <div className="text-center">
                      <h1 className="h4 text-gray-900 mb-4">Welcome Back!</h1>
                    </div>
                    <form className="user" onSubmit={handleSubmmit}>
                      <div className="form-group">
                        <input type="text"
                          className={'form-control form-control-user' }
                          id="exampleInputFullName"
                          aria-describedby="emailHelp" placeholder="Nhập họ tên .... "
                          onChange={handleInputChange}
                          name="fullname"
                        />
                      </div>
                      <div className="form-group">
                        <input type="email"
                          className={'form-control form-control-user' }
                          id="exampleInputEmail"
                          aria-describedby="emailHelp" placeholder="Nhập email ..."
                          onChange={handleInputChange}
                          name="email"
                        />
                      </div>
                      <div className="form-group">
                        <input type="password"
                          className={'form-control form-control-user'  }
                          id="exampleInputPassword" placeholder="Password"
                          onChange={handleInputChange}
                          name="password"
                        />
                      </div>

                      <div className="form-group">
                        <input type="password"
                          className={'form-control form-control-user' }
                          id="exampleInputRePassword" placeholder="Nhập lại Password"
                          onChange={handleInputChange}
                          name="repassword"
                        />
                      </div>

                      <div>
                        <label className="form-label" htmlFor="customFile">Chọn một ảnh avatar</label>
                        <input type="file" className="form-control" id="customFile" onChange={handleUploadImage} />
                      </div>

                      <br/>
                      {
                        alert.message && <div className={`alert ${alert.type}`}>{alert.message}</div>
                      }

                      <button className="btn btn-primary btn-user btn-block" >
                        {
                          loading && <span className='spinner-border spinner-border-sm mr-1'></span>
                        }
                        Đăng ký
                      </button>
                      <hr />
                    
                    </form>
            
                    {/* <div className="text-center">
                                            <a className="small" href="forgot-password.html">Forgot Password?</a>
                                        </div> */}
 
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}

export { Signup }