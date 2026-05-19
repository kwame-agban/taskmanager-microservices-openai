import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AiAssistantService {
  private readonly apiUrl = '/api/ai/assistant/ask';

  constructor(private http: HttpClient) {}

  ask(question: string): Observable<string> {
    return this.http.post(this.apiUrl, { question }, { responseType: 'text' });
  }
}
