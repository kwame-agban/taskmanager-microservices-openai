import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { AiAssistantService } from '../services/ai-assistant.service';

@Component({
  selector: 'app-ai-assistant',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './ai-assistant.html',
  styleUrl: './ai-assistant.css'
})
export class AiAssistantComponent {

  query = '';
  reply = '';

  constructor(private aiService: AiAssistantService) {}

  ask(): void {
    this.aiService.askAssistant(this.query)
      .subscribe({
        next: (response) => {
          this.reply = response;
        },
        error: (err) => {
          console.error(err);
          this.reply = 'Erreur lors de l’appel IA';
        }
      });
  }
}
