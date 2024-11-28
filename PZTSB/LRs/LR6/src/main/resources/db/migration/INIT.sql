-- Add Cities
INSERT INTO public.city (id, name, created_at) VALUES ('582ef3b8-ee49-4f6a-907f-c9bae1c45bf2', 'Odesa', '2024-11-28 19:26:51.000000');
INSERT INTO public.city (id, name, created_at) VALUES ('a66565c4-ac35-45bd-8c89-77ab36db5481', 'Frankfurt', '2024-11-28 19:27:05.000000');
INSERT INTO public.city (id, name, created_at) VALUES ('ad787936-d386-4cc3-ba55-13823263640c', 'Paris', '2024-11-28 19:27:33.000000');
INSERT INTO public.city (id, name, created_at) VALUES ('543b4dfa-5ad3-405c-a534-54bbf2814734', 'Ottawa', '2024-11-28 19:27:49.000000');

-- Add Flights
INSERT INTO public.flight (id, departs_from_id, arrives_to_id, departure_time, arrival_time, price, flight_number, created_at) VALUES ('ffa54957-3f1a-4a49-987b-ce4b0008280f', '582ef3b8-ee49-4f6a-907f-c9bae1c45bf2', 'ad787936-d386-4cc3-ba55-13823263640c', '2024-11-28 19:29:50.000000', '2024-11-28 21:29:51.000000', 10000, 'A123B', '2024-11-28 19:30:13.000000');
INSERT INTO public.flight (id, departs_from_id, arrives_to_id, departure_time, arrival_time, price, flight_number, created_at) VALUES ('c3c9ddf9-a7e2-4a49-8dfd-21a94dfdf80e', 'a66565c4-ac35-45bd-8c89-77ab36db5481', '543b4dfa-5ad3-405c-a534-54bbf2814734', '2024-11-30 12:31:22.000000', '2024-11-28 20:31:30.000000', 25000, 'AB123', '2024-11-28 19:31:46.000000');
INSERT INTO public.flight (id, departs_from_id, arrives_to_id, departure_time, arrival_time, price, flight_number, created_at) VALUES ('a79248ad-8c0e-4e8f-a220-021f11392678', 'ad787936-d386-4cc3-ba55-13823263640c', 'a66565c4-ac35-45bd-8c89-77ab36db5481', '2024-11-29 16:32:15.000000', '2024-11-29 19:32:10.000000', 6500, 'NVA12', '2024-11-28 19:31:55.000000');
