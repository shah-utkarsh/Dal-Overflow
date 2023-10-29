import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faLayerGroup } from '@fortawesome/free-solid-svg-icons';
import {Link} from 'react-router-dom';
import {useContext} from 'react';
import UserLoggedIn from './UserLoggedIn';
import SearchBar from './SearchBar';

function Header(){
    const {user} = useContext(UserLoggedIn);

    return(
        <div className='jsBeginnerWantToInsertTeParent'>
            <div className='Header' >
                <div className='LogoCol'>
                    <Link style={{display: 'flex', alignItems: 'center',textDecoration: 'none' }} to={'/'}>
                        <FontAwesomeIcon icon={faLayerGroup} size="3x" color='#ffd401' />
                        <div class='LogoText'><span>DalOverflow</span></div>
                    </Link>
                </div>
                <SearchBar/>
                {user && (
                    <div class="button1" >
                        <Link style={{textDecoration: 'none'}} to={'/profile' } className="profile">Hi, {user.userName.charAt(0).toUpperCase()+ user.userName.slice(1)}</Link>
                    </div>
                )}

                {!user && (
                    <div className='LoginHeader' >
                        <Link style={{textDecoration: 'none'}} to={'/login'}>
                            <div class="button1" >Login</div><span> </span>
                        </Link>
                        <Link style={{textDecoration: 'none'}} to={'/register'}>
                            <div class="button1" >Register</div>
                        </Link>    
                    </div>

                )}      
            </div>
        </div>
    );
}

export default Header;