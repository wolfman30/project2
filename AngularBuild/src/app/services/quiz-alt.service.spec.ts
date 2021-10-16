import { TestBed } from '@angular/core/testing';

import { QuizAltService } from './quiz-alt.service';

describe('QuizAltService', () => {
  let service: QuizAltService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(QuizAltService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
