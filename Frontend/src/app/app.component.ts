import { Component } from '@angular/core';
import { ShareDataService } from './Services/ShareDataService/share-data.service';
import { TokenStorageService } from './Services/TokenStorageService/token-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Library';
  isMenuCollapsed = false;

  constructor(private shareDataService: ShareDataService, private tokenStorageService: TokenStorageService) {}


  public isLogged() {
    return this.shareDataService.getLoggedIn();
  }

  public getRole() {
    return this.shareDataService.getRole();
  }
  
  public logout() {
    this.shareDataService.setLoggedIn(false);
    this.shareDataService.setRole('');
    this.tokenStorageService.logout();
  }
}
