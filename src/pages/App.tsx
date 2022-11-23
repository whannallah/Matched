import * as React from 'react';
import { Link} from 'react-router-dom';
import Main from './Main';
export default function App() {
  return (
    <>  
      <div>
        <ul>
          <li><Link to='/'>Home</Link></li>
          <li><Link to='/topics'>Topics</Link></li>
          <li><Link to='/settings'>Settings</Link></li>
        </ul>
        <hr />
        <Main />       
      </div>   
    </>
  )
}