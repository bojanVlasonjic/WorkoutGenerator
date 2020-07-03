import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ReviewDto } from '../dtos/review.dto';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  constructor(private http: HttpClient) { }

  createReview(reviewDto: ReviewDto): Observable<any> {
    return this.http.post('api/review', reviewDto);
  }

}
