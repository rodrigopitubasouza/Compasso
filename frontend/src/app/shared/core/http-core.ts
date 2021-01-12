import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';

import { Observable } from 'rxjs';

import { environment } from 'src/environments/environment';

export abstract class HttpCore {

    private applicationUrl = environment.applicationUrl;
    private readonly headers: HttpHeaders;

    constructor(protected httpClient: HttpClient, private controller: string) {
        this.controller = controller;
    }

    protected get<T>(endpoint: string, param?): Observable<T> {
        const targetEndpoint = endpoint ? `/${endpoint}` : '';
        const targetId = param ? `/${param}` : '';
        return this.httpClient.get<T>(`${this.applicationUrl}/${this.controller}${targetEndpoint}${targetId}`);
    }

    protected post<T, R>(endpoint: string, body?: T, param?): Observable<R> {
        const targetEndpoint = endpoint ? `/${endpoint}` : '';
        const targetId = param ? `/${param}` : '';
        return this.httpClient.post<R>(`${this.applicationUrl}/${this.controller}${targetEndpoint}${targetId}`, body);
    }

    protected put<T, R>(endpoint: string, body?: any, id?: number): Observable<R> {
        const targetEndpoint = endpoint ? `/${endpoint}` : '';
        const targetId = id ? `/${id}` : '';
        return this.httpClient.put<R>(`${this.applicationUrl}/${this.controller}${targetEndpoint}${targetId}`, body);
    }

    protected delete<T>(endpoint: string, id?: string): Observable<T> {
        const targetEndpoint = endpoint ? `/${endpoint}` : '';
        const targetId = id ? `/${id}` : '';
        return this.httpClient.delete<T>(`${this.applicationUrl}/${this.controller}${targetEndpoint}${targetId}`);
    }
}
