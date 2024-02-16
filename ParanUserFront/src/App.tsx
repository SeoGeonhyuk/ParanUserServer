import { useState, ChangeEvent } from 'react'
import usePageStore from './stores/usePageStore';
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'




export function FindPasswordPage(){
  const { whatPage, setWhatPage } = usePageStore();
const onClickBack = () => {
  setWhatPage('login');
}
  const [email, setEmail] = useState('');
  const onEmailChange = (event: ChangeEvent<HTMLInputElement>) => {
    setEmail(event.target.value);
  }
  const find = async () => {
    const response = await fetch(`http://localhost:8080/api/users/find-password?email=${email}`);
    // const result = await response.json();
    // console.log(result);
    setWhatPage('login');
  }
  return(
    <>
      <div>비밀번호 찾기</div>
      <div>이메일을 입력해주세요</div>
      <input type="text" placeholder="이메일" onChange={onEmailChange}/>
      <button onClick={onClickBack}>뒤로 가기</button>
      <button onClick={find}>비밀번호 찾기</button>
    </>
  )
}

export function RegisterPage(){
  const { whatPage, setWhatPage } = usePageStore();
const onClickBack = () => {
  setWhatPage('login');
}
  const [userName, setUserName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const onUserNameChange = (event: ChangeEvent<HTMLInputElement>) => {
    setUserName(event.target.value);
  }
  const onEmailChange = (event: ChangeEvent<HTMLInputElement>) => {
    setEmail(event.target.value);
  }
  const onPasswordChange = (event: ChangeEvent<HTMLInputElement>) => {
    setPassword(event.target.value);
  }

  const register = async () => {
    try {
      const message = { username: userName, email: email, password: password };
      const response = await fetch("http://localhost:8080/api/users/signup", {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(message),
      });
  
      // const result = await response.json();
      // console.log(result);
  
      // 응답을 받은 후에 페이지를 변경
      setWhatPage('login');
    } catch (error) {
      console.error('Error during registration:', error);
      // 에러 처리 로직 추가 (예: 사용자에게 알림)
    }
  };


  return(
    <>
      <div>회원가입 페이지</div>
      <div>사용자 이름, 이메일, 비밀번호를 입력해 주세요</div>
      <input type="text" placeholder="사용자 이름" onChange={onUserNameChange}/>
      <input type="text" placeholder="이메일" onChange={onEmailChange}/>
      <input type="text" placeholder="비밀번호" onChange={onPasswordChange}/>
      <button onClick={onClickBack}>뒤로 가기</button>
      <button onClick={register}>가입하기</button>
    </>
  )

}

export function UsernameInformationPage(){
  const { whatPage, setWhatPage } = usePageStore();
const onClickBack = () => {
  setWhatPage('login');
}
  const [userName, setUserName] = useState('');
  const onUserNameChange = (event: ChangeEvent<HTMLInputElement>) => {
    setUserName(event.target.value);
  }
  const findInformation = async () => {
    const response = await fetch(`http://localhost:8080/api/users/profile/username?username=${userName}`);
    const result = response.json();
    console.log(result);
    setWhatPage('login');
  }
  return(
    <>
      <div>사용자 이름으로 정보 찾기</div>
      <input type="text" placeholder="사용자 이름" onChange={onUserNameChange}/>
      <button onClick={onClickBack}>뒤로 가기</button>
      <button onClick={findInformation}>입력 완료</button>
    </>
  )

}

export function IdInformationPage(){
  const { whatPage, setWhatPage } = usePageStore();
const onClickBack = () => {
  setWhatPage('login');
}
  const [email, setEmail] = useState('');
  const onEmailChange = (event: ChangeEvent<HTMLInputElement>) => {
    setEmail(event.target.value);
  }
  const findInformation = async () => {
    const response = await fetch(`http://localhost:8080/api/users/profile/id?id=${escape(email)}`);
    const result = response.json();
    console.log(result);
    setWhatPage('login');
  }
  return(
    <>
      <div>회원번호로 정보 찾기</div>
      <input type="text" placeholder="회원번호" onChange={onEmailChange}/>
      <button onClick={onClickBack}>뒤로 가기</button>
      <button onClick={findInformation}>입력 완료</button>
    </>
  )

}

function App() {
  const { whatPage, setWhatPage } = usePageStore();
  const [userName, setUserName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const onUserNameChange = (event: ChangeEvent<HTMLInputElement>) => {
    setUserName(event.target.value);
  }
  const onEmailChange = (event: ChangeEvent<HTMLInputElement>) => {
    setEmail(event.target.value);
  }
  const onPasswordChange = (event: ChangeEvent<HTMLInputElement>) => {
    setPassword(event.target.value);
  }

  const register = async () => {
    //api 호출
    const message = {username: userName, email: email, password: password};
    const response = await fetch("http://localhost:8080/api/users/login", {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(message),
    });
    setWhatPage('login');
  };
  return(
    <>
    {whatPage === 'login' && <input type="text" placeholder="사용자 이름" onChange={onUserNameChange}/>}
    {whatPage === 'login' && <input type="text" placeholder="이메일" onChange={onEmailChange}/>}
    {whatPage === 'login' && <input type="text" placeholder="비밀번호" onChange={onPasswordChange}/>}
    {whatPage === 'login' && <button onClick={register}>로그인</button>}
    {whatPage === 'login' && <button onClick={() => setWhatPage('findPassword')}>비밀번호 찾기</button>}
    {whatPage === 'login' && <button onClick={() => setWhatPage('register')}>회원가입</button>}
    {whatPage === 'login' && <button onClick={() => setWhatPage('userNameInfo')}>유저 이름으로 정보 찾기</button>}
    {whatPage === 'login' && <button onClick={() => setWhatPage('idInfo')}>회원번호로 정보 찾기</button>}
    {whatPage === 'findPassword' && <FindPasswordPage/>}
    {whatPage === 'register' && <RegisterPage/>}
    {whatPage === 'userNameInfo' && <UsernameInformationPage/>}
    {whatPage === 'idInfo' && <IdInformationPage/>}
    </>
  )
  // const [count, setCount] = useState(0)

  // return (
  //   <>
  //     <div>
  //       <a href="https://vitejs.dev" target="_blank">
  //         <img src={viteLogo} className="logo" alt="Vite logo" />
  //       </a>
  //       <a href="https://react.dev" target="_blank">
  //         <img src={reactLogo} className="logo react" alt="React logo" />
  //       </a>
  //     </div>
  //     <h1>Vite + React</h1>
  //     <div className="card">
  //       <button onClick={() => setCount((count) => count + 1)}>
  //         count is {count}
  //       </button>
  //       <p>
  //         Edit <code>src/App.tsx</code> and save to test HMR
  //       </p>
  //     </div>
  //     <p className="read-the-docs">
  //       Click on the Vite and React logos to learn more
  //     </p>
  //   </>
  // )
}

export default App
