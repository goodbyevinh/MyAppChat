import React, { ChangeEvent, FormEvent, useEffect, useState } from 'react'
import { useDispatch } from 'react-redux'
import { useSelector } from 'react-redux'
import { useLocation } from 'react-router'
import { logout, login } from '../../store/account/action'
import { selectorLoadingAccount } from '../../store/account/selector';
import { selectorAlertMessage } from '../../store/alert/selector'
import { selectorAlert } from './../../store/alert/selector';
import config from './../../config/index';
import { useSearchParams } from 'react-router-dom';



type InputType = {
    email: string;
    password: string;
}


export function Login() {


    const [input, SetInput] = useState<InputType>({
        email: '',
        password: ''
    })

    const [submmited, setSubmitted] = useState(false)

    const loading = useSelector(selectorLoadingAccount)


    const alert = useSelector(selectorAlert)

    const dispatch = useDispatch();

    const location = useLocation();

    useEffect(() => {
        dispatch(logout())
    }, [])

    const handleInputChange = (e: ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target

        SetInput({
            ...input,
            [name]: value
        })
    }

    const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        const { email, password } = input

        setSubmitted(true);

        if (email && password) {

            const form = config.path.home
            dispatch(login(email, password, form))
        }
    }


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
                                        <form className="user" onSubmit={handleSubmit}>
                                            <div className="form-group">
                                                <input type="email"
                                                    className={'form-control form-control-user' + (submmited && !input.email ? ' is-invalid' : '')}
                                                    id="exampleInputEmail"
                                                    aria-describedby="emailHelp" placeholder="Enter Email Address..."
                                                    onChange={handleInputChange}
                                                    name="email"
                                                />
                                            </div>
                                            <div className="form-group">
                                                <input type="password"
                                                    className={'form-control form-control-user' + (submmited && !input.password ? ' is-invalid' : '')}
                                                    id="exampleInputPassword" placeholder="Password"
                                                    onChange={handleInputChange}
                                                    name="password"
                                                />
                                            </div>
                                            <div className="form-group">
                                                <div className="custom-control custom-checkbox small">
                                                    <input type="checkbox" className="custom-control-input" id="customCheck" />
                                                    <label className="custom-control-label" htmlFor="customCheck">Remember
                                                        Me</label>
                                                </div>
                                            </div>

                                            <button className="btn btn-primary btn-user btn-block" >
                                                {
                                                    loading && <span className='spinner-border spinner-border-sm mr-1'></span>
                                                }
                                                Login
                                            </button>
                                            <hr />
                                            <a  className="btn btn-google btn-user btn-block"
        href={process.env.REACT_APP_API_URL + 'oauth2/authorize/google?redirect_uri=' + process.env.REACT_APP_OAUTH2_REDIRECT_URI}
                                            >
                                                <i className="fab fa-google fa-fw" /> Login with Google
                                            </a>
                                            {/* <a href="index.html" className="btn btn-facebook btn-user btn-block">
                                                <i className="fab fa-facebook-f fa-fw" /> Login with Facebook
                                            </a> */}
                                            <hr/>
                                            {
                                                alert.message && <div className={`alert ${alert.type}`}>{alert.message}</div>
                                            }
                                        </form>
                                        <hr />
                                        {/* <div className="text-center">
                                            <a className="small" href="forgot-password.html">Forgot Password?</a>
                                        </div> */}
                                        <div className="text-center">
                                            <a className="small" href={config.path.signup}>Create an Account!</a>
                                        </div>
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
