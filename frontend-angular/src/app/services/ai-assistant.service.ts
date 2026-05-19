import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AiAssistantService {

  private apiUrl = '/api/ai/assistant';

  constructor(private http: HttpClient) {}

  askAssistant(question: string): Observable<string> {
    return this.http.post(
      `${this.apiUrl}/ask`,
      { question },
      { responseType: 'text' }
    );
  }
}
