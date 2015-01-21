class Tournament

  def initialize
    @players = []
  end

  def add_player player
    @players<<player
  end

  def average_rating
    @players.inject(0){|sum,player| sum+=player.rating}/@players.length
  end

  def check_consistency
   # (rounds_less_than_players || wins_eql_losses) == true ?  'The data is all ok!':'There was an issue with the data'
    wins_eql_losses+ rounds_less_than_players

  end

  def wins_eql_losses
    @players.inject(0) do |total, player|
     total+=(player.results.inject(0) { |wins_loss, result| result==0 ? wins_loss-=1 : wins_loss+=1 })
    end == 0 ? 'The number of wins equal the number of losses.' : 'Number of losses dont equal number of wins'

=begin   wins_losses = 0
    @players.each do |player|
     wins_losses += player.results.inject(0) do |total, wins|
       wins==0 ? total-=1 : total+=1
     end
    end
    wins_losses==0? 'The number of wins equal the number of losses.' : 'There was an error.The number of losses dont equal the number of wins'
=end
  end

  def rounds_less_than_players
    match = true
=begin    @players.each  do |player|
      (@players.length-1 == player.results.length)? match = true : match = false
    end
=end
    @players.inject(true){|match,player| @players.length-1 == player.results.length ? match = true : match = false}==true ?'Rounds and players match up':'Rounds and players dont math'
    #match == true ? 'Rounds and players match up':'Rounds and players dont math'
  end

  def sort!
    @players.sort!{|player,player_two| player_two.score<=>player.score}
  end
  def new_rating
    @players.each do |player|
      player.rating = r player
    end
  end
  def r player
    z = player.rating + 40*(player.score - (player.results.length * (e player) ))
    z.to_i
  end

  def e player
    average_without_player = @players.select{|p| p!=player}
   r_b = average_without_player.inject(0) {|sum,pl| sum+=pl.rating} /average_without_player.length
    x = ((r_b - player.rating).to_f / 400).to_f
    e_a = 1.fdiv(1+10**((r_b - player.rating)).fdiv(400) )
    e_a

  end

  def to_s
   @players.inject("Average rating: #{average_rating} \n"){|str,player| str+=player.to_s+"\n"}
  end

end