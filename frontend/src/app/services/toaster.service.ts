import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ToasterService {

  isDisplayed: boolean;

  constructor() {
    this.isDisplayed = false;
  }

  showMessage(header: string, content: string) {

    document.getElementById('toaster-header').innerHTML = header;
    document.getElementById('toaster-message').innerHTML = content;
    
    if (!this.isDisplayed) {
      document.getElementById('toaster').style.height = '25%';

      window.setTimeout(() => this.hideMessage(), 4000);
      this.isDisplayed = true;
    }

  }

  /** Used to display error messages received when subscribing to http requests
   * @param: err - HttpResponseError  */
  showErrorMessage(err: any) {

    if (err != null && err.error != null) {

      // if the user received a validation error, it has multiple messages inside it (message1,2,3...)
      if (err.error.message == null) {
        // display the first message from validation messages
        this.showMessage(err.statusText, err.error.message1);

      // the user received an apiException with message
      } else {
        this.showMessage(err.statusText, err.error.message);
      }

    }

  }

  private hideMessage() {
    if (this.isDisplayed) {
      document.getElementById('toaster').style.height = '0';
      this.isDisplayed = false;
    }

  }
}
