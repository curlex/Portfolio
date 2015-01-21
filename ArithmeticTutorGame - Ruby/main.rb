require_relative 'arithmetic_tutor.rb'
game_information = Array.new
IO.foreach('tutor.dat') do |line|
  game_information .push line
end
if !game_information.empty?
q = ArithmeticTutor.new game_information[0],game_information[1].to_i,game_information[2].to_i
q.play
else
  puts 'File is empty couldnt start game.'
end
